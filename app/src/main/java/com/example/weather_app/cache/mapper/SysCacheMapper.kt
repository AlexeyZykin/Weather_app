package com.example.weather_app.cache.mapper

import com.example.weather_app.cache.room.model.SysCache
import com.example.weather_app.data.model.SysEntity

class SysCacheMapper : Mapper<SysCache, SysEntity> {
    override fun mapFromCache(data: SysCache): SysEntity {
        return SysEntity(data.sunrise, data.sunset)
    }

    override fun mapToCache(data: SysEntity): SysCache {
        return SysCache(data.sunrise, data.sunset)
    }
}