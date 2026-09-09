# Append GSRD SoCFPGA device tree source include files
# As this is custom to Altera SoCFPGA GSRD, hence it is not suitable to be upstreamed to meta-intel-fpga

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-socfpga-lts:"

DEPENDS = "u-boot-mkimage-native dtc-native"

HYP_BUILD ??= "0"

SRC_URI:append:agilex7_dk_si_agf014eb = " file://fit_kernel_agilex7_dk_si_agf014eb.its ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex7_dk_si_agi027fc = " file://fit_kernel_agilex7_dk_si_agi027fc.its file://agilex7_edac.scc ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex7_dk_dev_agf023fa = " file://fit_kernel_agilex7_dk_dev_agf023fa.its file://agilex7_edac.scc ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex7_dk_dev_agm039fes = " file://fit_kernel_agilex7_dk_dev_agm039fes.its"
SRC_URI:append:agilex7_dk_dev_agm039ea = " file://fit_kernel_agilex7_dk_dev_agm039ea.its"
SRC_URI:append:agilex5 = " file://fit_kernel_agilex5.its"
SRC_URI:append:agilex5_dk_a5e065bb32aes1 = " ${@bb.utils.contains('IMAGE_TYPE', 'nand', 'file://fit_kernel_agilex5_dk_a5e065bb32aes1_nand.its', 'file://fit_kernel_agilex5_dk_a5e065bb32aes1.its', d)} ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e065bb32aes1_b0 = " file://fit_kernel_agilex5_dk_a5e065bb32aes1_b0.its ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bm16aea = " file://fit_kernel_agilex5_dk_a5e013bm16aea.its ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes = " ${@bb.utils.contains('IMAGE_TYPE', 'nand', 'file://fit_kernel_agilex5_dk_a5e013bb32aes_nand.its', 'file://fit_kernel_agilex5_dk_a5e013bb32aes.its', d)} ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes_5s = " ${@bb.utils.contains('IMAGE_TYPE', 'nand', 'file://fit_kernel_agilex5_dk_a5e013bb32aes_5s_nand.its', 'file://fit_kernel_agilex5_dk_a5e013bb32aes_5s.its', d)} ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex5_mk_a5e065bb32aes1 = " file://fit_kernel_agilex5_mk_a5e065bb32aes1.its ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:agilex3 = " file://fit_kernel_agilex3.its ${@bb.utils.contains('HYP_BUILD', '1', 'file://xen.scc', '', d)}"
SRC_URI:append:stratix10_htile = " file://fit_kernel_stratix10_htile.its"
SRC_URI:append:n5x = " file://fit_kernel_n5x.its"

SRC_URI:append:arria10 = " \
			${@bb.utils.contains("IMAGE_TYPE", "gsrd", "file://0001-socfpga_arria10_socdk-include-reference-design-dtsi.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "pr", "file://0001-dts-arm-enable-FPGA-PR-DTBs-for-Arria10.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "nand", "file://0001-socfpga_arria10_socdk-include-reference-design-dtsi.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "qspi", "file://0001-socfpga_arria10_socdk-include-reference-design-dtsi.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "pcie", "file://0001-socfpga_arria10_socdk-pcie-include-reference-design-.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "sgmii", "file://0001-socfpga_arria10_socdk-sgmii-include-reference-design.patch", "", d)} \
			${@bb.utils.contains("IMAGE_TYPE", "tse", "file://0001-socfpga_arria10_socdk-tse-include-reference-design-d.patch", "", d)} \
			"

SRC_URI:append:cyclone5 = " file://0001-socfpga_cyclone5_socdk-include-reference-design-dtsi.patch"

# Append GSRD specific kernel config fragments
SRC_URI:append = " file://ubifs.scc"
SRC_URI:append:agilex7_dk_si_agf014eb = " file://sgmii.scc file://ilc.scc file://blk_dev_ram.scc file://agilex7_edac.scc"
SRC_URI:append:agilex5 = " file://initrd.scc \
                           file://xdp.scc \
                           file://tsn.scc \
                           file://sensors.scc \
                           ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e065bb32aes1 = " file://initrd.scc \
                                  file://xdp.scc \
                                  file://tsn.scc \
                                  file://sensors.scc \
                                  file://edac.scc \
                                  ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e065bb32aes1_b0 = " file://initrd.scc \
                                  file://xdp.scc \
                                  file://tsn.scc \
                                  file://sensors.scc \
                                  file://edac.scc \
                                  ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bm16aea = " file://initrd.scc \
                                  file://xdp.scc \
                                  file://tsn.scc \
                                  file://sensors.scc \
                                  file://edac.scc \
                                  ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes = " file://initrd.scc \
                                  file://xdp.scc \
                                  file://tsn.scc \
                                  file://sensors.scc \
                                  file://edac.scc \
                                  ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes_5s = " file://initrd.scc \
                                  file://xdp.scc \
                                  file://tsn.scc \
                                  file://sensors.scc \
                                  file://edac.scc \
                                  ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex5_mk_a5e065bb32aes1 = " file://initrd.scc \
                                   file://xdp.scc \
                                   file://tsn.scc \
                                   file://sensors.scc \
                                   file://edac.scc \
                                   ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:agilex3 = " file://initrd.scc \
                           file://xdp.scc \
                           file://tsn.scc \
                           file://sensors.scc \
                           file://edac.scc \
                           ${@bb.utils.contains('IMAGE_TYPE', 'gsrd', 'file://usbedac.scc', '', d)}"
SRC_URI:append:stratix10_htile = " file://sgmii.scc file://ilc.scc"
SRC_URI:append:arria10 = " file://tse.scc"
SRC_URI:append:cyclone5 = " file://tse.scc"

inherit deploy

LINUXDEPLOYDIR = "${DEPLOYDIR}"
DTBDEPLOYDIR = "${DEPLOY_DIR_IMAGE}/devicetree"

do_deploy[depends] += "${@'' if d.getVar('MACHINE') in ['agilex5', 'arria10', 'cyclone5', 'n5x'] else 'hw-ref-design:do_deploy device-tree:do_deploy'}"

do_deploy:append() {
	# Stage required binaries for kernel.itb
	# Supported device family:
	# 				-	Agilex7
	# 				-	Agilex5
	# 				-	Stratix10

	if [[ "${MACHINE}" == *"agilex7_"* ]]; then
		# linux.dtb
		cp ${DTBDEPLOYDIR}/socfpga_agilex7_socdk.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_agilex7_vanilla.dtb ${B}
		if [[ "${MACHINE}" == "agilex7_dk_si_agf014e"* ]]; then
			cp ${DTBDEPLOYDIR}/socfpga_agilex7_socdk_nand.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex7_socdk_emmc.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex7_socdk_sgmii.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex7_socdk_pr.dtb ${B}
		fi
		# core.rbf
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
		if [[ "${MACHINE}" == *"agilex7_dk_si_agf014e"* ]]; then
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/sgmii.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_pr.core.rbf ${B}
		fi
	elif [ "${MACHINE}" = "n5x" ]; then
		cp ${LINUXDEPLOYDIR}/socfpga_n5x_socdk.dtb ${B}
	elif [[ "${MACHINE}" == "agilex5" ]]; then
		# linux.dtb
		cp ${LINUXDEPLOYDIR}/socfpga_${MACHINE}_socdk.dtb ${B}/socfpga_${MACHINE}_socdk.dtb
	elif [[ "${MACHINE}" == "agilex3" ]]; then
		# linux.dtb
		cp ${DTBDEPLOYDIR}/socfpga_agilex3_socdk.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_agilex3_vanilla.dtb ${B}
		# core.rbf
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
	elif [[ "${MACHINE}" == *"agilex5_"* ]]; then
		if [[ "${MACHINE}" == "agilex5_mk_a5e065bb32aes1" ]]; then
			# linux.dtb
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_vanilla_modular.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_modular.dtb ${B}
			# core.rbf
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
		elif [[ "${MACHINE}" == "agilex5_dk_a5e013bm16aea" ]]; then
			# linux.dtb
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_013b.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_vanilla_013b.dtb ${B}
			# core.rbf
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
		elif [[ "${MACHINE}" == "agilex5_dk_a5e065bb32aes1_b0" ]]; then
			# linux.dtb
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_vanilla.dtb ${B}
			# core.rbf
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
		elif [[ "${MACHINE}" == *"agilex5_dk_a5e013bb32aes"* ]]; then
			# linux.dtb
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_vanilla.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_emmc_vanilla.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_emmc.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_tsn_cfg2.dtb ${B}
			# core.rbf
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/tsnconfig2.core.rbf ${B}
			#NAND dtb and core.rbf
			if [ "${IMAGE_TYPE}" == "nand" ]; then
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_nand_vanilla.dtb ${B}
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_nand.dtb ${B}
				cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf ${B}
			fi
		else
			# linux.dtb
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_a0.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_vanilla_a0.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_emmc_vanilla_a0.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_emmc_a0.dtb ${B}
			cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_tsn_cfg2_a0.dtb ${B}
			# core.rbf
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf ${B}
			cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/tsnconfig2.core.rbf ${B}
			#NAND dtb and core.rbf
			if [ "${IMAGE_TYPE}" == "nand" ]; then
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_nand_vanilla_a0.dtb ${B}
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_nand_a0.dtb ${B}
				cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf ${B}
			fi
			if [[ "${MACHINE}" == "agilex5_dk_a5e065bb32aes1" ]]; then
				# linux.dtb
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_debug_vanilla_a0.dtb ${B}
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_aic0_a0.dtb ${B}
				cp ${DTBDEPLOYDIR}/socfpga_agilex5_socdk_debug_a0.dtb ${B}
				# core.rbf
				cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/aic0.core.rbf ${B}
				cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/debug2.core.rbf ${B}
			fi
		fi
	elif [[ "${MACHINE}" == *"stratix10"* ]]; then
		# linux.dtb
		cp ${DTBDEPLOYDIR}/socfpga_stratix10_socdk.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_stratix10_vanilla.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_stratix10_socdk_nand.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_stratix10_socdk_emmc.dtb ${B}
		cp ${DTBDEPLOYDIR}/socfpga_stratix10_socdk_pr.dtb ${B}
		# core.rbf
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd.core.rbf ${B}
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf ${B}
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf ${B}
		cp ${DEPLOY_DIR_IMAGE}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_pr.core.rbf ${B}
	fi

	# Generate and deploy kernel.itb
	if [[ "${MACHINE}" == *"agilex"* || "${MACHINE}" == *"stratix10"* ]]; then
		# kernel.its
		if [ "${IMAGE_TYPE}" == "nand" ]; then
			cp ${UNPACKDIR}/fit_kernel_${MACHINE}_nand.its ${B}/fit_kernel_${MACHINE}.its
		else
			cp ${UNPACKDIR}/fit_kernel_${MACHINE}.its ${B}
		fi
		# Image
		cp ${LINUXDEPLOYDIR}/Image ${B}
		# Compress Image to lzma format
		if [ -e ${B}/Image.lzma ]; then
			rm ${B}/Image.lzma
		fi
		xz --format=lzma ${B}/Image
		# Generate kernel.itb. The fit_kernel_*.its files declare RSA
		# signatures (key-name-hint = "dev"); mkimage -k <keydir> -r
		# actually signs the configurations and marks the key required so
		# U-Boot (CONFIG_FIT_SIGNATURE) rejects an unsigned/tampered kernel
		# FIT. UBOOT_SIGN_KEYDIR defaults to the dev key in
		# meta-intel-fpga-refdes (files/keys/README.md); override for
		# production.
		if [ "${UBOOT_SIGN_KEYDIR%/}" = "${SOCFPGA_REFDES_DEV_KEYDIR%/}" ]; then
			bbwarn "UBOOT_SIGN_KEYDIR is the committed GSRD development key (${UBOOT_SIGN_KEYDIR}/${UBOOT_SIGN_KEYNAME}). This is NOT a security boundary — override UBOOT_SIGN_KEYDIR/UBOOT_SIGN_KEYNAME for production (see files/keys/README.md)."
		fi
		mkimage -f ${B}/fit_kernel_${MACHINE}.its -k "${UBOOT_SIGN_KEYDIR}" -r ${B}/kernel.itb
		# Deploy kernel.its, kernel.itb and Image.lzma
		install -m 744 ${B}/fit_kernel_${MACHINE}.its ${DEPLOYDIR}
		install -m 744 ${B}/kernel.itb ${DEPLOYDIR}
		install -m 744 ${B}/Image.lzma ${DEPLOYDIR}
	elif [ "${MACHINE}" = "n5x" ]; then
		# Uncompressed Image FIT (matches former U-Boot binman kernel.itb)
		cp ${UNPACKDIR}/fit_kernel_${MACHINE}.its ${B}
		cp ${LINUXDEPLOYDIR}/Image ${B}
		mkimage -f ${B}/fit_kernel_${MACHINE}.its ${B}/kernel.itb
		install -m 744 ${B}/fit_kernel_${MACHINE}.its ${DEPLOYDIR}
		install -m 744 ${B}/kernel.itb ${DEPLOYDIR}
	fi
}

do_install:append() {
	if ${@bb.utils.contains("IMAGE_TYPE", "pr", "true", "false", d)} ; then
		install -D -m 0644 ${D}/boot/fpga_static_region.dtb ${D}/boot/fpga_static_region.dtbo
		install -D -m 0644 ${D}/boot/persona1.dtb ${D}/boot/persona1.dtbo
		install -D -m 0644 ${D}/boot/persona0.dtb ${D}/boot/persona0.dtbo
	fi
}
