#!/bin/bash

systemctl stop wpa_supplicant-ap@wlan0

sleep 15

systemctl restart systemd-networkd

sleep 15
