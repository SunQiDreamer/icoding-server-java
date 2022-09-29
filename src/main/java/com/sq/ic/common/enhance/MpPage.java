package com.sq.ic.common.enhance;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sq.ic.common.util.Streams;
import com.sq.ic.pojo.vo.PageVo;
import com.sq.ic.pojo.vo.req.page.PageReqVo;
import com.sq.ic.pojo.vo.req.page.TeacherPageReqVo;

import java.util.List;
import java.util.function.Function;

public class MpPage<T> extends Page<T> {
    private final PageReqVo reqVo;

    public MpPage(PageReqVo reqVo) {
        super(reqVo.getPage(), reqVo.getSize());
        this.reqVo = reqVo;
    }

    public  <N> PageVo<N> commonBuldVo(List<N> data) {
        reqVo.setPage(getCurrent());
        reqVo.setSize(getSize());

        PageVo<N> pageVo = new PageVo<>();
        pageVo.setCount(getTotal());
        pageVo.setPages(getPages());
        pageVo.setData(data);
        return pageVo;
    }

    public PageVo<T> buildVo() {
        return commonBuldVo(getRecords());
    }

    public <R> PageVo<R> buildVo(Function<T, R> function) {
        List records = getRecords(); // po
        List data = Streams.map(records, function); // to vo
        return commonBuldVo(data); // to PageVo
    }
}
