SUMMARY = "U-boot boot scripts for Altera SoCFPGA devices"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

DEPENDS = "u-boot-mkimage-native dtc-native"

# The SoC64 boot script (boot.scr) is packaged as a signed FIT: the .its files
# carry a 'configurations' node with an RSA signature, and mkimage -k <keydir>
# -r signs it and marks the key required. U-Boot proper (built with
# CONFIG_BOOTSCR_FIT_SIGNATURE) then refuses to 'source' an unsigned or
# tampered boot.scr. UBOOT_SIGN_KEYDIR/UBOOT_SIGN_KEYNAME default to the
# development key in this layer (see files/keys/README.md) and must be
# overridden with a private key for production.

inherit deploy nopackages
PACKAGE_ARCH = "${MACHINE_ARCH}"

HYP_BUILD ??= "0"

SRC_URI:agilex7_dk_si_agf014eb = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex7_dk_si_agi027fc = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex7_dk_dev_agm039fes = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex7_dk_dev_agm039ea = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex7_dk_dev_agf023fa = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5 = "file://agilex5_uboot.txt file://agilex5_uboot_script.its"
SRC_URI:agilex5_dk_a5e065bb32aes1 = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5_dk_a5e065bb32aes1_b0 = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5_dk_a5e013bm16aea = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5_dk_a5e013bb32aes = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5_dk_a5e013bb32aes_5s = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex5_mk_a5e065bb32aes1 = "file://uboot.txt file://uboot_script.its"
SRC_URI:agilex3 = "file://agilex3_uboot.txt file://agilex3_uboot_script.its"
SRC_URI:stratix10_htile = "file://uboot.txt file://uboot_script.its"
SRC_URI:arria10 = "file://arria10_u-boot.txt"
SRC_URI:cyclone5 = "file://cyclone5_u-boot.txt"
SRC_URI:n5x = "file://n5x_u-boot.txt"

SRC_URI:append:agilex3 = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex3_uboot_xen.txt file://agilex3_uboot_script_xen.its', '', d)}"
SRC_URI:append:agilex5_dk_a5e065bb32aes1 = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_uboot_xen.txt file://agilex5_uboot_script_xen.its file://agilex5_uboot_xen_emmc.txt file://agilex5_uboot_script_xen_emmc.its file://agilex5_uboot_xen_debug.txt file://agilex5_uboot_script_xen_debug.its', '', d)}"
SRC_URI:append:agilex5_dk_a5e065bb32aes1_b0 = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_uboot_xen.txt file://agilex5_uboot_script_xen.its file://agilex5_uboot_xen_emmc.txt file://agilex5_uboot_script_xen_emmc.its file://agilex5_uboot_xen_debug.txt file://agilex5_uboot_script_xen_debug.its', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bm16aea = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_013b_uboot_xen.txt file://agilex5_013b_uboot_script_xen.its', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_uboot_xen.txt file://agilex5_uboot_script_xen.its file://agilex5_uboot_xen_emmc.txt file://agilex5_uboot_script_xen_emmc.its file://agilex5_uboot_xen_debug.txt file://agilex5_uboot_script_xen_debug.its', '', d)}"
SRC_URI:append:agilex5_dk_a5e013bb32aes_5s = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_uboot_xen.txt file://agilex5_uboot_script_xen.its file://agilex5_uboot_xen_emmc.txt file://agilex5_uboot_script_xen_emmc.its file://agilex5_uboot_xen_debug.txt file://agilex5_uboot_script_xen_debug.its', '', d)}"
SRC_URI:append:agilex5_mk_a5e065bb32aes1 = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex5_modular_uboot_xen.txt file://agilex5_modular_uboot_script_xen.its', '', d)}"
SRC_URI:append:agilex7_dk_si_agf014eb = " ${@bb.utils.contains('HYP_BUILD', '1', 'file://agilex7_uboot_xen.txt file://agilex7_uboot_script_xen.its', '', d)}"

S = "${UNPACKDIR}/sources-unpack"

do_configure[noexec] = "1"
do_install[noexec] = "1"

# Warn once per signing task when the committed GSRD development key is used.
# Call from every do_compile* that runs mkimage -k (machine overrides replace
# the default do_compile, so the warn must not live only in the default).
socfpga_warn_if_dev_fit_key() {
	if [ "${UBOOT_SIGN_KEYDIR%/}" = "${SOCFPGA_REFDES_DEV_KEYDIR%/}" ]; then
		bbwarn "UBOOT_SIGN_KEYDIR is the committed GSRD development key (${UBOOT_SIGN_KEYDIR}/${UBOOT_SIGN_KEYNAME}). This is NOT a security boundary — override UBOOT_SIGN_KEYDIR/UBOOT_SIGN_KEYNAME for production (see files/keys/README.md)."
	fi
}

do_compile:n5x() {
	mkimage -A arm -O linux -T script -C none -a 0 -e 0 -n "N5X Script" -d "${UNPACKDIR}/${MACHINE}_u-boot.txt" ${UNPACKDIR}/u-boot.scr
}

do_compile() {
	socfpga_warn_if_dev_fit_key
	if [[ "${MACHINE}" == *"agilex7_"* ]]; then
		mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
		export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
		if [[ "${HYP_BUILD}" = "1" ]]; then
			mkimage -f "${UNPACKDIR}/agilex7_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
		fi
	fi
}

do_compile:agilex5() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/agilex5_uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
}

do_compile:agilex5_dk_a5e065bb32aes1() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_emmc.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.emmc.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_debug.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.debug.uimg
	fi
}

do_compile:agilex5_dk_a5e065bb32aes1_b0() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_emmc.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.emmc.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_debug.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.debug.uimg
	fi
}

do_compile:agilex5_dk_a5e013bm16aea() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex5_013b_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
	fi
}

do_compile:agilex5_dk_a5e013bb32aes() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_emmc.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.emmc.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_debug.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.debug.uimg
	fi
}

do_compile:agilex5_dk_a5e013bb32aes_5s() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_emmc.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.emmc.uimg
		mkimage -f "${UNPACKDIR}/agilex5_uboot_script_xen_debug.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.debug.uimg
	fi
}

do_compile:agilex5_mk_a5e065bb32aes1() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
        export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
        if [[ "${HYP_BUILD}" = "1" ]]; then
                mkimage -f "${UNPACKDIR}/agilex5_modular_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
        fi
}

do_compile:agilex3() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/agilex3_uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	if [[ "${HYP_BUILD}" = "1" ]]; then
		mkimage -f "${UNPACKDIR}/agilex3_uboot_script_xen.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.xen.uimg
	fi
}

do_compile:stratix10_htile() {
	socfpga_warn_if_dev_fit_key
	mkimage -f "${UNPACKDIR}/uboot_script.its" -k "${UBOOT_SIGN_KEYDIR}" -r ${UNPACKDIR}/boot.scr.uimg
}

do_compile:cyclone5() {
	mkimage -A arm -O linux -T script -C none -a 0 -e 0 -n "Cyclone5 Script" -d "${UNPACKDIR}/${MACHINE}_u-boot.txt" ${UNPACKDIR}/u-boot.scr
}

do_compile:arria10() {
	mkimage -A arm -O linux -T script -C none -a 0 -e 0 -n "Arria10 Script" -d "${UNPACKDIR}/${MACHINE}_u-boot.txt" ${UNPACKDIR}/boot.scr
}

do_deploy() {
	export HYP_BUILD="${@bb.utils.contains('HYP_BUILD', '1', '1', '0', d)}"
	install -d ${DEPLOYDIR}
	if [[ "${MACHINE}" == "arria10" ]]; then
		install -m 0755 ${UNPACKDIR}/${MACHINE}_u-boot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/boot.scr ${DEPLOYDIR}/boot.scr
	elif [[ "${MACHINE}" == *"agilex7_"* ]] || [[ "${MACHINE}" == *"stratix10"* ]]; then
		install -m 0755 ${UNPACKDIR}/uboot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/boot.scr.uimg ${DEPLOYDIR}/boot.scr.uimg
		if [[ "${HYP_BUILD}" = "1" ]]; then
			install -m 0755 ${UNPACKDIR}/agilex7_uboot_xen.txt ${DEPLOYDIR}/u-boot_xen.txt
			install -m 0644 ${UNPACKDIR}/boot.scr.xen.uimg ${DEPLOYDIR}/boot.scr.xen.uimg
		fi
	elif [[ "${MACHINE}" == "agilex5" ]]; then
		install -m 0755 ${UNPACKDIR}/agilex5_uboot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/boot.scr.uimg ${DEPLOYDIR}/boot.scr.uimg
	elif [[ "${MACHINE}" == *"agilex5_"* ]]; then
		install -m 0755 ${UNPACKDIR}/uboot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/boot.scr.uimg ${DEPLOYDIR}/boot.scr.uimg
		if [[ "${HYP_BUILD}" = "1" ]]; then
			install -m 0644 ${UNPACKDIR}/boot.scr.xen.uimg ${DEPLOYDIR}/boot.scr.xen.uimg
			if [[ "${MACHINE}" == "agilex5_dk_a5e013bm16aea" ]]; then
				install -m 0755 ${UNPACKDIR}/agilex5_013b_uboot_xen.txt ${DEPLOYDIR}/u-boot_xen.txt
			elif [[ "${MACHINE}" == "agilex5_mk_a5e065bb32aes1" ]]; then
				install -m 0755 ${UNPACKDIR}/agilex5_modular_uboot_xen.txt ${DEPLOYDIR}/u-boot_xen.txt
			else
				install -m 0755 ${UNPACKDIR}/agilex5_uboot_xen.txt ${DEPLOYDIR}/u-boot_xen.txt
				install -m 0755 ${UNPACKDIR}/agilex5_uboot_xen_emmc.txt ${DEPLOYDIR}/u-boot_xen_emmc.txt
				install -m 0644 ${UNPACKDIR}/boot.scr.xen.emmc.uimg ${DEPLOYDIR}/boot.scr.xen.emmc.uimg
				install -m 0755 ${UNPACKDIR}/agilex5_uboot_xen_debug.txt ${DEPLOYDIR}/u-boot_xen_debug.txt
				install -m 0644 ${UNPACKDIR}/boot.scr.xen.debug.uimg ${DEPLOYDIR}/boot.scr.xen.debug.uimg
			fi
		fi
	elif [[ "${MACHINE}" == *"agilex3"* ]]; then
		install -m 0755 ${UNPACKDIR}/agilex3_uboot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/boot.scr.uimg ${DEPLOYDIR}/boot.scr.uimg
		if [[ "${HYP_BUILD}" = "1" ]]; then
			install -m 0755 ${UNPACKDIR}/agilex3_uboot_xen.txt ${DEPLOYDIR}/u-boot_xen.txt
			install -m 0644 ${UNPACKDIR}/boot.scr.xen.uimg ${DEPLOYDIR}/boot.scr.xen.uimg
		fi
	elif [[ "${MACHINE}" == "n5x" ]] || [[ "${MACHINE}" == "cyclone5" ]]; then
		install -m 0755 ${UNPACKDIR}/${MACHINE}_u-boot.txt ${DEPLOYDIR}/u-boot.txt
		install -m 0644 ${UNPACKDIR}/u-boot.scr ${DEPLOYDIR}/u-boot.scr
	else
		:
	fi
}

addtask do_deploy after do_compile before do_build
