FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS:append:agilex7_dk_si_agf014eb = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex7_dk_si_agi027fc = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex7_dk_dev_agm039fes = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex7_dk_dev_agm039ea = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex7_dk_dev_agf023fa = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5 = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_dk_a5e065bb32aes1 = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_dk_a5e065bb32aes1_b0 = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_dk_a5e013bm16aea = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_dk_a5e013bb32aes = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_dk_a5e013bb32aes_5s = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex5_mk_a5e065bb32aes1 = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:agilex3 = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:stratix10_htile = " arm-trusted-firmware bash u-boot-socfpga-scr"
DEPENDS:append:arria10 = " hw-ref-design"
DEPENDS:append:n5x = " arm-trusted-firmware bash u-boot-socfpga-scr"

inherit deploy

do_compile[deptask] = "do_deploy"

# fdt_add_pubkey (from u-boot tools) is used to embed the FIT verification
# public key into U-Boot's control device tree. Same as meta-altera-fpga.
DEPENDS:append = " u-boot-tools-native"

# Embed the FIT verification public key into U-Boot's control device tree so
# that U-Boot proper (CONFIG_FIT_SIGNATURE / CONFIG_BOOTSCR_FIT_SIGNATURE) has
# a 'required' trust anchor to verify the signed kernel FIT and boot.scr. The
# key defaults to the development key in meta-intel-fpga-refdes
# (UBOOT_SIGN_KEYDIR/UBOOT_SIGN_KEYNAME, see files/keys/README.md); override it
# with a private key for production.
#
# For SoC64 the runtime control FDT is the "fdt-0" image packaged *inside*
# u-boot.itb (SPL loads it via CONFIG_SPL_LOAD_FIT). binman builds that image
# from the board device tree named by CONFIG_DEFAULT_DEVICE_TREE
# (arch/arm/dts/<dt>.dtb) - NOT from the standalone u-boot.dtb. The key must
# therefore be injected into that board dtb and u-boot.itb repackaged by
# re-running binman. Simply injecting into u-boot.dtb (as an earlier version of
# this recipe did) leaves fdt-0 - and thus the enforced trust anchor - empty.
#
# Verified locally on an Agilex5 build: injecting into
# arch/arm/dts/socfpga_agilex5_socdk.dtb and forcing a binman repackage yields
# a u-boot.itb whose fdt-0 carries the trust anchor; a kernel.itb signed with
# the dev key then verifies against it while a tampered payload is rejected.
socfpga_warn_if_dev_fit_key() {
	# N2: committed RSA private key is intentional for GSRD bring-up, but must
	# stay loudly non-production whenever a recipe uses it.
	if [ "${UBOOT_SIGN_KEYDIR%/}" = "${SOCFPGA_REFDES_DEV_KEYDIR%/}" ]; then
		bbwarn "UBOOT_SIGN_KEYDIR is the committed GSRD development key (${UBOOT_SIGN_KEYDIR}/${UBOOT_SIGN_KEYNAME}). This is NOT a security boundary — override UBOOT_SIGN_KEYDIR/UBOOT_SIGN_KEYNAME for production (see files/keys/README.md)."
	fi
}

# Resolve the board dtb that becomes fdt-0. Same candidate list as
# meta-altera-fpga (vendor-qualified CONFIG_DEFAULT_DEVICE_TREE / OF_UPSTREAM).
socfpga_find_board_dtb() {
	config_dir="$1"
	dt="$2"
	dt_base="${dt##*/}"
	for cand in \
		"${config_dir}/dts/upstream/src/arm64/${dt}.dtb" \
		"${config_dir}/dts/upstream/src/arm/${dt}.dtb" \
		"${config_dir}/arch/arm/dts/${dt_base}.dtb" \
		"${config_dir}/arch/arm/dts/${dt}.dtb" \
		"${config_dir}/dts/upstream/src/arm64/intel/${dt_base}.dtb" \
		"${config_dir}/dts/upstream/src/arm/intel/${dt_base}.dtb" \
		"${config_dir}/${dt}.dtb" \
		"${config_dir}/${dt_base}.dtb"
	do
		if [ -f "${cand}" ]; then
			printf '%s' "${cand}"
			return 0
		fi
	done
	found=$(find "${config_dir}" -type f -name "${dt_base}.dtb" 2>/dev/null | head -1)
	if [ -n "${found}" ] && [ -f "${found}" ]; then
		printf '%s' "${found}"
		return 0
	fi
	return 1
}

socfpga_embed_one_itb() {
	config_dir="$1"
	dt=$(sed -n 's/^CONFIG_DEFAULT_DEVICE_TREE="\(.*\)"/\1/p' "${config_dir}/.config")
	if [ -z "${dt}" ]; then
		bbwarn "socfpga_embed_fit_pubkey: skip: CONFIG_DEFAULT_DEVICE_TREE empty in ${config_dir}/.config"
		return 1
	fi
	boarddtb=$(socfpga_find_board_dtb "${config_dir}" "${dt}") || boarddtb=""
	if [ -z "${boarddtb}" ]; then
		bbwarn "socfpga_embed_fit_pubkey: skip: missing board dtb for ${dt} under ${config_dir}"
		return 1
	fi
	# fdt_add_pubkey is a U-Boot host tool (not dtc). Prefer the binary from
	# this recipe's just-finished build (meta-altera-fpga).
	fdt_add_pubkey_bin=""
	for cand in \
		"${config_dir}/tools/fdt_add_pubkey" \
		"${B}/tools/fdt_add_pubkey" \
		"${STAGING_BINDIR_NATIVE}/fdt_add_pubkey"
	do
		if [ -x "${cand}" ]; then
			fdt_add_pubkey_bin="${cand}"
			break
		fi
	done
	if [ -z "${fdt_add_pubkey_bin}" ]; then
		bbfatal "socfpga_embed_fit_pubkey: fdt_add_pubkey not found under ${config_dir}/tools, ${B}/tools, or ${STAGING_BINDIR_NATIVE} (CONFIG_TOOLS_LIBCRYPTO host tool)"
	fi
	bbnote "socfpga_embed_fit_pubkey: embedding ${UBOOT_SIGN_KEYNAME} into ${boarddtb} via ${fdt_add_pubkey_bin}"
	"${fdt_add_pubkey_bin}" -a sha256,rsa2048 -k "${UBOOT_SIGN_KEYDIR}" \
		-n "${UBOOT_SIGN_KEYNAME}" -r conf "${boarddtb}"
	touch "${boarddtb}"
	rm -f "${config_dir}/u-boot.itb" "${config_dir}/.binman_stamp"
	oe_runmake -C "${S}" O="${config_dir}" .binman_stamp
	return 0
}

socfpga_embed_fit_pubkey() {
	if [ ! -f "${UBOOT_SIGN_KEYDIR}/${UBOOT_SIGN_KEYNAME}.crt" ]; then
		bbwarn "FIT signing key ${UBOOT_SIGN_KEYNAME} not found in ${UBOOT_SIGN_KEYDIR}; control dtb left without a trust anchor"
		return
	fi

	socfpga_warn_if_dev_fit_key

	embeds=0
	saw_itb=0
	# GSRD multi-config uses UBOOT_MACHINE; meta-altera-fpga uses UBOOT_DEFCONFIG.
	if [ -n "${UBOOT_MACHINE}" ]; then
		for config in ${UBOOT_MACHINE}; do
			config_dir="${B}/${config}"
			if [ ! -f "${config_dir}/u-boot.itb" ]; then
				bbwarn "socfpga_embed_fit_pubkey: skip ${config}: missing ${config_dir}/u-boot.itb"
				continue
			fi
			saw_itb=1
			if socfpga_embed_one_itb "${config_dir}"; then
				embeds=$(expr "${embeds}" + 1)
			fi
		done
	else
		config_dir=$(ls -d ${B}/*${UBOOT_DEFCONFIG}* 2>/dev/null | head -1)
		if [ -z "$config_dir" ]; then
			config_dir="${B}/${UBOOT_DEFCONFIG}"
		fi
		if [ -f "${config_dir}/u-boot.itb" ]; then
			saw_itb=1
			if socfpga_embed_one_itb "${config_dir}"; then
				embeds=$(expr "${embeds}" + 1)
			fi
		else
			bbwarn "socfpga_embed_fit_pubkey: skip: missing ${config_dir}/u-boot.itb"
		fi
	fi

	# A10/C5 do not produce u-boot.itb — skip. SoC64 with an ITB must embed.
	if [ "${saw_itb}" -eq 0 ]; then
		return
	fi
	if [ "${embeds}" -eq 0 ]; then
		bbfatal "socfpga_embed_fit_pubkey: embedded zero FIT pubkeys (key ${UBOOT_SIGN_KEYNAME} was present in ${UBOOT_SIGN_KEYDIR}). SoC64 machines that produce u-boot.itb must embed a trust anchor into fdt-0."
	fi
}

do_compile:append() {
	socfpga_embed_fit_pubkey
}

do_configure:prepend:agilex5() {
	sed -i -e '$aCONFIG_LEGACY_IMAGE_FORMAT=y' ${S}/configs/socfpga_agilex5_defconfig
}

do_compile:prepend:arria10() {
	cp -r ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/hps.xml ${S}/.
	${S}/arch/arm/mach-socfpga/qts-filter-a10.sh ${S}/hps.xml ${S}/arch/arm/dts/socfpga_arria10_socdk_sdmmc_handoff.h
}

do_compile:append:arria10() {
	cp ${DEPLOY_DIR_IMAGE}/Image ${S}/Image

	if ${@bb.utils.contains("IMAGE_TYPE", "nand", "true", "false", d)} || ${@bb.utils.contains("IMAGE_TYPE", "qspi", "true", "false", d)}; then
		# A10 NAND Variant
		cp ${B}/socfpga_${MACHINE}_${IMAGE_TYPE}_defconfig-${UBOOT_CONFIG}/u-boot-nodtb.bin ${S}/u-boot-nodtb.bin
		cp ${B}/socfpga_${MACHINE}_${IMAGE_TYPE}_defconfig-${UBOOT_CONFIG}/u-boot.dtb ${S}/u-boot.dtb
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_10as066n2.core.rbf ${S}/ghrd_10as066n2.core.rbf
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_10as066n2.periph.rbf ${S}/ghrd_10as066n2.periph.rbf
		cp ${DEPLOY_DIR_IMAGE}/socfpga_arria10_socdk_${IMAGE_TYPE}.dtb ${S}/socfpga_arria10_socdk_${IMAGE_TYPE}.dtb
		mkimage -E -f ${S}/board/altera/${MACHINE}-socdk/fit_uboot.its ${B}/fit_uboot_${IMAGE_TYPE}.itb
		mkimage -E -f ${S}/board/altera/${MACHINE}-socdk/fit_spl_fpga.its ${B}/fit_spl_fpga_${IMAGE_TYPE}.itb
		mkimage -E -f ${S}/board/altera/${MACHINE}-socdk/fit_kernel_${IMAGE_TYPE}.its ${B}/kernel_${IMAGE_TYPE}.itb
	else
		cp ${B}/socfpga_${MACHINE}_defconfig-${UBOOT_CONFIG}/u-boot-nodtb.bin ${S}/u-boot-nodtb.bin
		cp ${B}/socfpga_${MACHINE}_defconfig-${UBOOT_CONFIG}/u-boot.dtb ${S}/u-boot.dtb
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_10as066n2.core.rbf ${S}/ghrd_10as066n2.core.rbf
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_10as066n2.periph.rbf ${S}/ghrd_10as066n2.periph.rbf
		mkimage -E -f ${S}/board/altera/${MACHINE}-socdk/fit_uboot.its ${B}/fit_uboot_${IMAGE_TYPE}.itb
		mkimage -E -f ${S}/board/altera/${MACHINE}-socdk/fit_spl_fpga_periph_only.its ${B}/fit_spl_fpga_${IMAGE_TYPE}.itb
	fi
}

do_deploy:append:arria10() {
	install -d ${DEPLOYDIR}
	if ${@bb.utils.contains("IMAGE_TYPE", "nand", "true", "false", d)} || ${@bb.utils.contains("IMAGE_TYPE", "qspi", "true", "false", d)}; then
		install -m 744 ${B}/kernel_${IMAGE_TYPE}.itb ${DEPLOYDIR}/kernel_${IMAGE_TYPE}.itb
		install -m 744 ${B}/fit_uboot_${IMAGE_TYPE}.itb ${DEPLOYDIR}/fit_uboot_${IMAGE_TYPE}.itb
		install -m 744 ${B}/fit_spl_fpga_${IMAGE_TYPE}.itb ${DEPLOYDIR}/fit_spl_fpga_${IMAGE_TYPE}.itb
	else
		install -m 744 ${B}/*.itb ${DEPLOYDIR}/
		install -m 644 ${B}/${config}-${UBOOT_CONFIG}/spl/u-boot-splx4.sfp ${DEPLOYDIR}/u-boot-splx4.sfp
	fi
}

do_deploy:append:cyclone5() {
	install -d ${DEPLOYDIR}
	install -m 644 ${B}/${config}-${UBOOT_CONFIG}/u-boot-with-spl.sfp ${DEPLOYDIR}/u-boot-with-spl.sfp
	install -m 644 ${B}/${config}-${UBOOT_CONFIG}/spl/u-boot-spl.sfp ${DEPLOYDIR}/u-boot-spl.sfp
	install -m 644 ${B}/${config}-${UBOOT_CONFIG}/spl/u-boot-splx4.sfp ${DEPLOYDIR}/u-boot-splx4.sfp
}
