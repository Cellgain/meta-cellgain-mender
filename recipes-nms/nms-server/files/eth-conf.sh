#!/bin/bash

sudo rm -v /etc/systemd/network/eth.network

sudo cp -v /data/nms-server/eth.network /etc/systemd/network/eth.network

sudo systemctl restart systemd-networkd

sudo journalctl -u systemd-networkd -n 15