#!/bin/sh

sudo dd if=/dev/zero of=/dev/mmcblk1 bs=4096 count=4096

sudo tar xzOf /etc/emmc/img-yocto.sdimg.tar.gz | dd of=/dev/mmcblk1 bs=1M

sudo mkdir /media/data/
sudo mount /dev/mmcblk1p4 /media/data

sudo rm /media/data/u-boot/fw_env.config
sudo cp /etc/emmc/fw_env.config /media/data/u-boot/

sudo umount /media/data

echo "Success!"
echo "Power off, remove SD card and power up" 

shutdown -P now

