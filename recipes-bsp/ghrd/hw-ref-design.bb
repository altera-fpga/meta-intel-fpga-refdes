SUMMARY = "Altera SoCFPGA Golden Hardware Reference Design (GHRD)"
DESCRIPTION = "Prebuilt FPGA bitstream for SOC Development Kit"
SECTION = "bsp"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit deploy

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

IMAGE_TYPE ?= "gsrd"

RBO_RELEASE_VER ?= "2026.10"
GHRD_REPO ?= "https://releases.rocketboards.org/release/${RBO_RELEASE_VER}/rbf-source"

ARM64_GHRD_CORE_RBF = "ghrd.core.rbf"
A10_GHRD_CORE_RBF = "ghrd_10as066n2.core.rbf"
A10_GHRD_PERIPH_RBF = "ghrd_10as066n2.periph.rbf"
C5_GHRD_CORE_RBF = "soc_system.rbf"

SRC_URI:agilex5_dk_a5e065bb32aes1 ?= "\
		${GHRD_REPO}/agilex5_dk_a5e065bb32aes1_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e065bb32aes1_gsrd_core \
		${GHRD_REPO}/agilex5_dk_a5e065bb32aes1_emmc_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e065bb32aes1_emmc_core \
		${GHRD_REPO}/agilex5_dk_a5e065bb32aes1_tsnconfig2_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e065bb32aes1_tsnconfig2_core \
		file://agilex5_dk_a5e065bb32aes1_aic0_ghrd.core.rbf \
		file://agilex5_dk_a5e065bb32aes1_debug2_ghrd.core.rbf \
		"

SRC_URI:agilex5_dk_a5e065bb32aes1_b0 ?= "\
		${GHRD_REPO}/agilex5_dk_a5e065bb32aes1_b0_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e065bb32aes1_b0_gsrd_core \
		"

SRC_URI:agilex5_dk_a5e013bm16aea ?= "\
		${GHRD_REPO}/agilex5_dk_a5e013bm16aea_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bm16aea_gsrd_core \
		"

SRC_URI:agilex5_dk_a5e013bb32aes ?= "\
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_gsrd_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_nand_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_nand_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_emmc_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_emmc_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_tsnconfig2_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_tsnconfig2_core \
		"

SRC_URI:agilex5_dk_a5e013bb32aes_5s ?= "\
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_5s_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_5s_gsrd_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_5s_nand_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_5s_nand_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_5s_emmc_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_5s_emmc_core \
		${GHRD_REPO}/agilex5_dk_a5e013bb32aes_5s_tsnconfig2_${ARM64_GHRD_CORE_RBF};name=agilex5_dk_a5e013bb32aes_5s_tsnconfig2_core \
		"

SRC_URI:agilex5_mk_a5e065bb32aes1 ?= "\
		${GHRD_REPO}/agilex5_mk_a5e065bb32aes1_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex5_mk_a5e065bb32aes1_gsrd_core \
		"

SRC_URI:agilex7_dk_si_agf014eb ?= "\
		${GHRD_REPO}/agilex7_dk_si_agf014eb_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agf014eb_gsrd_core \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_nand_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agf014eb_nand_core \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_emmc_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agf014eb_emmc_core \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_sgmii_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agf014eb_sgmii_core \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_pr_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agf014eb_pr_core \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_pr_persona0.rbf;name=agilex7_dk_si_agf014eb_pr_persona0 \
		${GHRD_REPO}/agilex7_dk_si_agf014eb_pr_persona1.rbf;name=agilex7_dk_si_agf014eb_pr_persona1 \
		"

SRC_URI:agilex7_dk_si_agi027fc ?= "\
		${GHRD_REPO}/agilex7_dk_si_agi027fc_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_si_agi027fc_gsrd_core \
		"

SRC_URI:agilex7_dk_dev_agm039fes ?= "\
		${GHRD_REPO}/agilex7_dk_dev_agm039fes_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_dev_agm039fes_gsrd_core \
		"

SRC_URI:agilex7_dk_dev_agm039ea ?= "\
		${GHRD_REPO}/agilex7_dk_dev_agm039ea_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_dev_agm039ea_gsrd_core \
		"

SRC_URI:agilex7_dk_dev_agf023fa ?= "\
		${GHRD_REPO}/agilex7_dk_dev_agf023fa_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex7_dk_dev_agf023fa_gsrd_core \
		"

SRC_URI:stratix10_htile ?= "\
		${GHRD_REPO}/stratix10_htile_gsrd_${ARM64_GHRD_CORE_RBF};name=stratix10_htile_gsrd_core \
		${GHRD_REPO}/stratix10_htile_nand_${ARM64_GHRD_CORE_RBF};name=stratix10_htile_nand_core \
		${GHRD_REPO}/stratix10_htile_emmc_${ARM64_GHRD_CORE_RBF};name=stratix10_htile_emmc_core \
		${GHRD_REPO}/stratix10_htile_pr_${ARM64_GHRD_CORE_RBF};name=stratix10_htile_pr_core \
		${GHRD_REPO}/stratix10_htile_pr_persona0.rbf;name=stratix10_htile_pr_persona0 \
		${GHRD_REPO}/stratix10_htile_pr_persona1.rbf;name=stratix10_htile_pr_persona1 \
		"

SRC_URI:arria10 ?= "\
		${GHRD_REPO}/arria10_${IMAGE_TYPE}_hps.xml;name=arria10_${IMAGE_TYPE}_hps_xml \
		${GHRD_REPO}/arria10_${IMAGE_TYPE}_${A10_GHRD_CORE_RBF};name=arria10_${IMAGE_TYPE}_core \
		${GHRD_REPO}/arria10_${IMAGE_TYPE}_${A10_GHRD_PERIPH_RBF};name=arria10_${IMAGE_TYPE}_periph \
		${@bb.utils.contains("IMAGE_TYPE", "pr", "${GHRD_REPO}/arria10_${IMAGE_TYPE}_persona0.rbf;name=arria10_pr_persona0", "", d)} \
		${@bb.utils.contains("IMAGE_TYPE", "pr", "${GHRD_REPO}/arria10_${IMAGE_TYPE}_persona1.rbf;name=arria10_pr_persona1", "", d)} \
		"

SRC_URI:agilex3 ?= "\
		${GHRD_REPO}/agilex3_gsrd_${ARM64_GHRD_CORE_RBF};name=agilex3_gsrd_core \
		"

SRC_URI:cyclone5 ?= "${GHRD_REPO}/cyclone5_${IMAGE_TYPE}_${C5_GHRD_CORE_RBF};name=cyclone5_${IMAGE_TYPE}_core"

SRC_URI[agilex3_gsrd_core.sha256sum] = "5d85db6b8e220371b76ef45d9c9e5b52031df53929a4d0f8fc723c91ada26a82"

SRC_URI[agilex5_dk_a5e013bm16aea_gsrd_core.sha256sum] = "384ca31826449daea13653a388cfb7955d509a4209e57523553835403fab0c72"

SRC_URI[agilex5_dk_a5e065bb32aes1_gsrd_core.sha256sum] = "6a49ab5fd77fc871a3f590ad427381caf9673e95319543045b3c5ff2e01d952a"
SRC_URI[agilex5_dk_a5e065bb32aes1_emmc_core.sha256sum] = "6459555f05b842ecd1848199e7c3e28e429d62ba1fc8e623ef13dd976f1383f3"
SRC_URI[agilex5_dk_a5e065bb32aes1_tsnconfig2_core.sha256sum] = "9d166eadca4e73bc5b9a5df9ac087b6b2183edda3fb2e65a47e7be9be3f8c709"

SRC_URI[agilex5_mk_a5e065bb32aes1_gsrd_core.sha256sum] = "73377e5bb4d4aa459c0760e5b253c2fc5d6440b926612350bb19aa78df0c20fe"

SRC_URI[agilex7_dk_si_agf014eb_emmc_core.sha256sum] = "8c091e4944389e37dcbd50d04c5c7d8d68648f9f94e1fc42acb5e0a37126513d"
SRC_URI[agilex7_dk_si_agf014eb_gsrd_core.sha256sum] = "e9c7516aeb8247fb6cc046358778c5ded846261efa5e47592c2560fd41336e6d"
SRC_URI[agilex7_dk_si_agf014eb_nand_core.sha256sum] = "d434d1933947d8542ef1fdce38cf07485be82d26d8c2b9bc89d583119a173da5"
SRC_URI[agilex7_dk_si_agf014eb_pr_core.sha256sum] = "32972116f73de9e4fc1a9bf0bb9163d335a3f973248ab154774e01209427376b"
SRC_URI[agilex7_dk_si_agf014eb_pr_persona0.sha256sum] = "009fabad34bcc813861b1e94a167b7faa483ad1c7ab944e216f742072ca2c8fc"
SRC_URI[agilex7_dk_si_agf014eb_pr_persona1.sha256sum] = "ad9716714f30c4f5b695cfdb981a0a0cfc28fce871daf2e1d6191b12b34af80e"
SRC_URI[agilex7_dk_si_agf014eb_sgmii_core.sha256sum] = "fb1c07f99e86331f764e8f0210eda1f26329a4ab75e2e9077d013060ed5cc0e3"

SRC_URI[agilex7_dk_si_agi027fc_gsrd_core.sha256sum] = "2bbfafe6435143625026ee2aac238d980b3d35adc111cb160a46bfe97ff04dfb"
SRC_URI[agilex7_dk_dev_agf023fa_gsrd_core.sha256sum] = "a10626999addf7874925262effb0cff61049e3db4bc4d205a8388c464872e315"

SRC_URI[agilex7_dk_dev_agm039ea_gsrd_core.sha256sum] = "3b05012f724d81d000ce4604513cf39977b4ff8a10105ad7cd1ff5a49565a49a"
SRC_URI[agilex7_dk_dev_agm039fes_gsrd_core.sha256sum] = "efbb0d2b7852a6ac14fafd9ca99f713301b9a834ce8279597449e63efeab4522"

SRC_URI[stratix10_htile_emmc_core.sha256sum] = "5d59593c9252fc2454530e833d35789cf151e5ac04091f8269ecce6ad4e9e2ef"
SRC_URI[stratix10_htile_gsrd_core.sha256sum] = "86c861b84d488b661602eb1fcb6ecae6fe5c8aefb8b2e41a96d00f028e3ea7e3"
SRC_URI[stratix10_htile_nand_core.sha256sum] = "4f4657826af3f59dee71aed3be7e4e7300e7fe65d226f5b9f46cba5b44e2beb6"
SRC_URI[stratix10_htile_pr_core.sha256sum] = "fb1613ad7cae90b4135a21d1e411791446400a5576af2d8e1bd661afc737fa9a"
SRC_URI[stratix10_htile_pr_persona0.sha256sum] = "fca1cc3976636e6337f0861fd445a3bf435bf6de32a4da49ed1fb0f2d50a99bd"
SRC_URI[stratix10_htile_pr_persona1.sha256sum] = "b77cd84626b40ab0f879e9393afc12cf52f6549492a5fb3e1c27c14aff996230"

SRC_URI[arria10_gsrd_core.sha256sum] = "e58ac9cd020f288126ba9aaf0f90c6a340abcca7922d5352541fca3903f12a3a"
SRC_URI[arria10_gsrd_periph.sha256sum] = "e58ac9cd020f288126ba9aaf0f90c6a340abcca7922d5352541fca3903f12a3a"
SRC_URI[arria10_gsrd_hps_xml.sha256sum] = "d7253d2860450491a79119ffb560a4a1175f4aa7e3e47fa99f93ede1f6af6be1"
SRC_URI[arria10_nand_core.sha256sum] = "f7dd315741c28af5e0ae10df9bb04b0c2534921fcdce5f0f9069cdee8ae384c5"
SRC_URI[arria10_nand_periph.sha256sum] = "0e8c6e3fd4e211eb278d1b24bc6c6569e5fcc786d28730e8fb53fc4aef45b656"
SRC_URI[arria10_nand_hps_xml.sha256sum] = "5aa42ae41250dc2e9dc71a0295c399b879cbcb9d61dca48a81f9295fe25877b2"
SRC_URI[arria10_pcie_core.sha256sum] = "8fd2b9bb4b04bad44b108b545e9e5f28bf364ee36743a92fc307958a97ed72e4"
SRC_URI[arria10_pcie_periph.sha256sum] = "e4837f4b0d8346743b11c35f8761d92e0319ec8eeb550c9be3df031658bcd4f8"
SRC_URI[arria10_pcie_hps_xml.sha256sum] = "557cef46332bb00a5d61c96a809a37fb9e4b755512217c035ea7e60b8efd235d"
SRC_URI[arria10_pr_core.sha256sum] = "826d032832558d41d4b32b99134be95d9b8803511236cb95bf593c99e842159a"
SRC_URI[arria10_pr_periph.sha256sum] = "ba9a0f35d28ab2aa36a1a43c031c24a149252f78864c39e6cd8abe577d730da9"
SRC_URI[arria10_pr_hps_xml.sha256sum] = "781b9e97b35c2457acaa1eb671719e8a50014b3e4ee0d3b411d44feb248122b4"
SRC_URI[arria10_pr_persona0.sha256sum] = "1558a3c3a8411405bc6dbbb4ad3fc6d8b583b01137246d3e2f64076b1afeab51"
SRC_URI[arria10_pr_persona1.sha256sum] = "7d57bf04480c5fdcda6e1e982c5f27e7af548cf43e85467db2c3aa21cf15bdf7"
SRC_URI[arria10_qspi_core.sha256sum] = "5ad392e5be6e5b0dd960aae6548031537ef3c885d248b1129326b4c19f5a536f"
SRC_URI[arria10_qspi_periph.sha256sum] = "cbb8ce36e38115623a4cc270e243e5a7df49ba8951187bd555819362a9d903d9"
SRC_URI[arria10_qspi_hps_xml.sha256sum] = "367b52a86788f01649c877e100741ccdd2fd60c95cc21c3740c02783eeb5592d"
SRC_URI[arria10_sgmii_core.sha256sum] = "8e6afd5b205e2a6c5b238f405d0cdae297d016b4b3a5d88d8391209dc14c0859"
SRC_URI[arria10_sgmii_periph.sha256sum] = "eff3c3437491b931c4cf84b2035c9859082712c81d0963b463baf2e7b48752d2"
SRC_URI[arria10_sgmii_hps_xml.sha256sum] = "410b32ef26c81d05e405547366f3b78b0d7b82bd77d4c5c61928b6a95ff1dbd6"
SRC_URI[arria10_tse_core.sha256sum] = "29ef7bdb47719508dfc74cffeae3db0df3ba3fa55598b28538253dcaebc89d60"
SRC_URI[arria10_tse_periph.sha256sum] = "97ec2052074c877bedb2aeaebf666395a033a518e9d621fdd5922f242555610c"
SRC_URI[arria10_tse_hps_xml.sha256sum] = "f0a03499294a7b7bfe2314fea56f10a3e2f8549b6f44b04cba9e96f90f9d7d65"

SRC_URI[cyclone5_gsrd_core.sha256sum] = "63dae5a869244e25f492f2d6499b12b89bc1f1c367419d2081689ea2d60da124"

S = "${UNPACKDIR}/sources"

PROVIDES = "virtual/bitstream"

FILES:${PN} = " \
		/boot \
		/usr/lib/firmware \
		"

PACKAGES = "${PN}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install () {
	if [[ "${MACHINE}" == *"agilex7_"* ]]; then
		if [[ "${MACHINE}" == *"agilex7_dk_si_agf014e"* ]]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${D}/boot/nand.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${D}/boot/emmc.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_sgmii_${ARM64_GHRD_CORE_RBF} ${D}/boot/sgmii.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_${ARM64_GHRD_CORE_RBF} ${D}/boot/ghrd_pr.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona0.rbf ${D}${base_libdir}/firmware/persona0.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona1.rbf ${D}${base_libdir}/firmware/persona1.rbf
		else
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
		fi
	fi

	if ${@bb.utils.contains("MACHINE", "stratix10_htile", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${D}/boot/nand.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${D}/boot/emmc.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_${ARM64_GHRD_CORE_RBF} ${D}/boot/ghrd_pr.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona0.rbf ${D}${base_libdir}/firmware/persona0.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona1.rbf ${D}${base_libdir}/firmware/persona1.rbf
	fi

	if ${@bb.utils.contains("MACHINE", "cyclone5", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_${C5_GHRD_CORE_RBF} ${D}/boot/${C5_GHRD_CORE_RBF}
	fi

	if ${@bb.utils.contains("IMAGE_TYPE", "pr", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_persona0.rbf ${D}${base_libdir}/firmware/persona0.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_persona1.rbf ${D}${base_libdir}/firmware/persona1.rbf
	fi
	
	if [[ "${MACHINE}" == *"agilex5_"* ]]; then
		if [ "${MACHINE}" == "agilex5_mk_a5e065bb32aes1" ]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
		elif [[ "${MACHINE}" == *"agilex5_dk_a5e"* ]]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
			if [[ "${MACHINE}" != "agilex5_dk_a5e013bm16aea" && "${MACHINE}" != "agilex5_dk_a5e065bb32aes1_b0" ]]; then
				if [ "${IMAGE_TYPE}" == "nand" ]; then
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${D}/boot/nand.core.rbf
				fi
				install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${D}/boot/emmc.core.rbf
				install -D -m 0644 ${UNPACKDIR}/${MACHINE}_tsnconfig2_ghrd.core.rbf ${D}/boot/tsnconfig2.core.rbf
				if [ "${MACHINE}" == "agilex5_dk_a5e065bb32aes1" ]; then
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_aic0_ghrd.core.rbf ${D}/boot/aic0.core.rbf
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_debug2_ghrd.core.rbf ${D}/boot/debug2.core.rbf
				fi
			fi
		fi
	fi

	if [[ "${MACHINE}" == *"agilex3"* ]]; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${D}/boot/${ARM64_GHRD_CORE_RBF}
	fi
}

do_deploy () {
	if [[ "${MACHINE}" == *"agilex3"* ]]; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${ARM64_GHRD_CORE_RBF}
	fi

	if [[ "${MACHINE}" == *"agilex5_"* ]]; then
		if [ "${MACHINE}" == "agilex5_mk_a5e065bb32aes1" ]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${ARM64_GHRD_CORE_RBF}
		elif [[ "${MACHINE}" == *"agilex5_dk_a5e"* ]]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${ARM64_GHRD_CORE_RBF}
			if [[ "${MACHINE}" != "agilex5_dk_a5e013bm16aea" && "${MACHINE}" != "agilex5_dk_a5e065bb32aes1_b0" ]]; then
				if [ "${IMAGE_TYPE}" == "nand" ]; then
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf
				fi
				install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf
				install -D -m 0644 ${UNPACKDIR}/${MACHINE}_tsnconfig2_ghrd.core.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/tsnconfig2.core.rbf
				if [ "${MACHINE}" == "agilex5_dk_a5e065bb32aes1" ]; then
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_aic0_ghrd.core.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/aic0.core.rbf
					install -D -m 0644 ${UNPACKDIR}/${MACHINE}_debug2_ghrd.core.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/debug2.core.rbf
				fi
			fi
		fi
	fi

	if [[ "${MACHINE}" == *"agilex7_"* ]]; then
		if [[ "${MACHINE}" == *"agilex7_dk_si_agf014e"* ]]; then
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_gsrd_ghrd/${ARM64_GHRD_CORE_RBF}
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_sgmii_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/sgmii.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_pr.core.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona0.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona0.rbf
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona1.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona1.rbf
		else
			install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_gsrd_ghrd/${ARM64_GHRD_CORE_RBF}
		fi
	fi

	if ${@bb.utils.contains("MACHINE", "stratix10_htile", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_gsrd_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_gsrd_ghrd/${ARM64_GHRD_CORE_RBF}
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_nand_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/nand.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_emmc_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/emmc.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_${ARM64_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/ghrd_pr.core.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona0.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona0.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_pr_persona1.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona1.rbf
	fi

	if ${@bb.utils.contains("MACHINE", "arria10", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_hps.xml ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/hps.xml
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_${A10_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${A10_GHRD_CORE_RBF}
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_${A10_GHRD_PERIPH_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${A10_GHRD_PERIPH_RBF}
	fi

	if ${@bb.utils.contains("MACHINE", "cyclone5", "true", "false", d)} ; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_${C5_GHRD_CORE_RBF} ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/${C5_GHRD_CORE_RBF}
	fi

	if ${@bb.utils.contains("IMAGE_TYPE", "pr", "true", "false", d)}; then
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_persona0.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona0.rbf
		install -D -m 0644 ${UNPACKDIR}/${MACHINE}_${IMAGE_TYPE}_persona1.rbf ${DEPLOYDIR}/${MACHINE}_${IMAGE_TYPE}_ghrd/persona1.rbf
	fi
}

addtask install after do_configure before do_deploy
addtask deploy after do_install
