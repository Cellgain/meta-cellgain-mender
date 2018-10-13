#!/bin/bash

rm /etc/wpa_supplicant/wpa_supplicant-nl80211-wlan0.conf

systemctl stop wpa_supplicant-ap@wlan0

systemctl disable wpa_supplicant-ap@wlan0

systemctl daemon-reload

systemctl restart systemd-networkd
