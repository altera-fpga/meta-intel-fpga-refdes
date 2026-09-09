The official Yocto Project BSP layer for Altera SoCFPGA Golden Software Reference Design (GSRD)

This layer works with Yocto Project Poky layer

Dependencies
============
* poky (git://git.yoctoproject.org/poky)
* meta-openembedded (https://github.com/openembedded/meta-openembedded.git)
    * meta-networking
    * meta-oe
    * meta-python
* meta-intel-fpga (https://git.yoctoproject.org/meta-intel-fpga)

Supported U-Boot and Linux Kernels
==================================
It is important to understand the support and release of u-boot-socfpga
and linux-socfpga in the github.com/altera-fpga repositories.  Only
currently supported and maintained releases/branches are available in the
repository. As such, supported kernel and u-boot releases are updated regularly
in currently supported meta-altera branches.  Kernel and u-boot releases
which are no longer supported are removed.

Supported Image Types
=====================
* console-image-minimal

    Consists of basic boot up essentials packages for Altera SoCFPGA platforms.

* gsrd-console-image

    Consists of reference designs application to show case the platform capabilities.

* xvfb-console-image

    Based on gsrd-console-image with additional support on X virtual framebuffer and its applications.

Signed boot (kernel FIT and boot.scr)
=====================================
For SoC64 devices (Stratix 10, Agilex 7, Agilex 5, Agilex 3) the kernel FIT
(`kernel.itb`) and the U-Boot boot script (`boot.scr`) are packaged as signed
FIT images:

* The `.its` files carry a `configurations` node with an `sha256,rsa2048`
  signature (`key-name-hint = "dev"`).
* `mkimage -k <keydir> -r` signs the configuration and marks the key required.
* The matching public key is embedded via `fdt_add_pubkey` into the board
  device tree named by U-Boot's `CONFIG_DEFAULT_DEVICE_TREE`
  (`arch/arm/dts/<dt>.dtb`), then `u-boot.itb` is repackaged so the runtime
  control FDT (`fdt-0` loaded by SPL) carries the trust anchor. Injecting into
  the standalone `u-boot.dtb` alone does **not** reach `fdt-0`.
* U-Boot proper then verifies the kernel FIT (`CONFIG_FIT_SIGNATURE`) and the
  boot script (`CONFIG_BOOTSCR_FIT_SIGNATURE`) before use.

This is a lockstep change with u-boot-socfpga: enabling
`CONFIG_BOOTSCR_FIT_SIGNATURE` in U-Boot requires this layer to ship a signed
`boot.scr`, and vice versa, or the board stops booting. Merge order:
u-boot-socfpga #937 (mechanisms, default-off) → this layer (signed artifacts +
pubkey in `fdt-0`) → u-boot-socfpga #929 (flip `CONFIG_BOOTSCR_FIT_SIGNATURE=y`).
Do not merge #929 before this layer lands.

The signing key defaults to `files/keys/dev.{key,crt}`, a **development-only**
key that is NOT a security boundary (see `files/keys/README.md`). Override
`UBOOT_SIGN_KEYDIR` / `UBOOT_SIGN_KEYNAME` with a private key for production.

Contribution
============
Submit patches via github pull requests, Use github issues to report problems or to send comments

Maintainer(s): drosdi <danish.ahmad.rosdi@intel.com>

