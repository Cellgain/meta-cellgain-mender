#!/bin/bash

systemctl start wpa_supplicant-ap@wlan0

sleep 15

systemctl restart systemd-networkd

sleep 15

