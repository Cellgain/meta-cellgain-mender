FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI_append = " 	file://uboot.cfg \
					file://uEnv.txt \
					"
UBOOT_ENV = "uEnv"
require u-boot-mender-beaglebone.inc
