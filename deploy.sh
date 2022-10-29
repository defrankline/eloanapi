#!/usr/bin/env bash
docker compose build
docker compose down -v --remove-orphans
docker compose up -d
