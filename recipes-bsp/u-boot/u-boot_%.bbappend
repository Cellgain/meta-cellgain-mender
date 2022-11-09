FILESEXTRAPATHS:prepend := "${THISDIR}:"
SRC_URI:append = " 	file://uboot.cfg \
					file://uEnv.txt \
					"
UBOOT_ENV = "uEnv"
require u-boot-mender-beaglebone.inc
