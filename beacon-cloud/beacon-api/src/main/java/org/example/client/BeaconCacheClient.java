package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * BeaconCacheClient
 *
 * @author Yang QingBo
 * @date 2026-05-26 09:47
 * @description
 */

@FeignClient("beacon-cache")
public class BeaconCacheClient {
}
