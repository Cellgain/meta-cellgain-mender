DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "splash ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    net-snmp-server \
    net-snmp-client \ 
    net-snmp-libs \ 
    net-snmp-mibs \
    minicom \ 
    libusb1 \ 
    libusb1-dev \
    usbutils \
    nano \
    "
KERNEL_DEVICETREE = "am335x-boneblack.dtb"

DISTRO_FEATURES = "ext2 usbhost ${DISTRO_FEATURES_LIBC} kernel-module-cdc-acm"

PREFERRED_PROVIDER:udev = "eudev"

# remove pulseaudio since it brings in x11
DISTRO_FEATURES_BACKFILL_CONSIDERED += "pulseaudio opengl"

PREFERRED_PROVIDER:jpeg = "libjpeg-turbo"

PACKAGE_CLASSES = "package_ipk"

# i686 or x86_64
SDKMACHINE = "x86_64"

# Use the same type here as specified in ARTIFACTIMG_FSTYPE to avoid
# building an unneeded image file.
SDIMG_ROOTFS_TYPE = "ext4"

DISTRO_FEATURES:append = " systemd"
VIRTUAL-RUNTIME:init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
VIRTUAL-RUNTIME:initscripts = ""

#autoload ethernet module at boot
KERNEL_MODULE_AUTOLOAD += " cdc_acm"

ARTIFACTIMG_FSTYPE = "ext4"

EXTRA_USERS_PARAMS = "usermod -P q1w2e3r4 root; "

INHERIT += "extrausers rm_work mender-full "

VIRTUAL-RUNTIME:snmp-manager = " net-snmp-server"

inherit core-image
