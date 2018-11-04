package com.atgugui.consumer;

import java.util.List;

import com.atgugui.result.RestResponse;
import com.atgugui.vo.TitleVO;

public interface HomePagetaTitleService {

	RestResponse<List<TitleVO>> getHomePagetaTitle();

}
