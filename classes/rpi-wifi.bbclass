# Global variables
DISTRO_FEATURES_append = " wifi"

IMAGE_INSTALL_append = " iw wpa-supplicant linux-firmware-bcm43430 packagegroup-base connman connman-client nms-server"

ENABLE_UART = "1"
