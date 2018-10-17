#!/bin/bash

systemctl start wpa_supplicant-ap@wlan0

sleep 5

systemctl restart systemd-networkd

sleep 5

