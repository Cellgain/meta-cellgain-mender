#!/bin/bash

systemctl enable wpa_supplicant-ap@wlan0

systemctl start wpa_supplicant-ap@wlan0

sleep 5

systemctl restart systemd-networkd

systemctl start wpa_supplicant-ap@wlan0

sleep 5

systemctl restart systemd-networkd
