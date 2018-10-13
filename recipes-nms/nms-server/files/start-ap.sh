#!/bin/bash

ln -s /data/nms-server/network/wpa_supplicant-wlan0.conf /etc/wpa_supplicant/wpa_supplicant-nl80211-wlan0.conf

systemctl enable wpa_supplicant-ap@wlan0

systemctl daemon-reload

systemctl start wpa_supplicant-ap@wlan0

systemctl restart systemd-networkd
