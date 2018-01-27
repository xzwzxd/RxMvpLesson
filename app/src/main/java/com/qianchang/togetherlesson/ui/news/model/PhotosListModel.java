package com.qianchang.togetherlesson.ui.news.model;

import com.qianchang.togetherlesson.api.Api;
import com.qianchang.togetherlesson.api.HostType;
import com.qianchang.togetherlesson.bean.GirlData;
import com.qianchang.togetherlesson.bean.PhotoGirl;
import com.qianchang.togetherlesson.ui.news.contract.PhotoListContract;
import com.jaydenxiao.common.baserx.RxSchedulers;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * des:图片
 * Created by xsf
 * on 2016.09.12:02
 */
public class PhotosListModel implements PhotoListContract.Model {
    @Override
    public Observable<List<PhotoGirl>> getPhotosListData(int size, int page) {
        return Api.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(Api.getCacheControl(),size, page)
                .map(new Func1<GirlData, List<PhotoGirl>>() {
                    @Override
                    public List<PhotoGirl> call(GirlData girlData) {
                        return girlData.getResults();
                    }
                })
                .compose(RxSchedulers.<List<PhotoGirl>>io_main());
    }
}
