# SoCFPGA GSRD FIT signing keys

`dev.key` / `dev.crt` are a **development-only** RSA-2048 key pair used to sign
the kernel FIT and the U-Boot boot script (`boot.scr`) and to embed the matching
public key into the runtime control FDT (`fdt-0` inside `u-boot.itb`, built from
the board device tree named by `CONFIG_DEFAULT_DEVICE_TREE`).

## THIS KEY IS NOT A SECURITY BOUNDARY

The private key is committed to this public layer. Anyone can use it to sign a
malicious FIT that U-Boot would then accept. It exists only to:

* exercise and test the signed-FIT boot path end to end, and
* give the GSRD a working signed image out of the box.

It protects against accidental corruption / casual tampering, **not** against a
deliberate attacker.

## Using a production key

To actually close CWE-494 / CWE-347 in the field, build with your own secret key
that never leaves your signing infrastructure. Override in `local.conf` or a
distro/BSP config:

    UBOOT_SIGN_KEYDIR  = "/path/to/your/private/keydir"
    UBOOT_SIGN_KEYNAME = "your-key"      # expects <keydir>/<name>.key and .crt

The `.its` files reference `key-name-hint = "dev"`; if your key uses a different
name, update the `key-name-hint` in the kernel and boot-script `.its` files to
match (or name your key `dev`). Rebuild U-Boot so the board dtb / `fdt-0` embed
step picks up your public key as the `required` trust anchor.
