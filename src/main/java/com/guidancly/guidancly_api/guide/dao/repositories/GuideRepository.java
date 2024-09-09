package com.guidancly.guidancly_api.guide.dao.repositories;

import com.guidancly.guidancly_api.common.dao.repositories.BaseRepository;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;

public interface GuideRepository extends BaseRepository<Guide,Long> {
    Guide findByEmail(String email);
    Guide findByEmailOrNumber(String email,String number);
}
