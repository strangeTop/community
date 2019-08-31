package com.community.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageDto {
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirst;
    private boolean showLast;
    private Integer page;
    private Integer total;
    private List<Integer> pageList = new ArrayList<>();
    private List<QuestionDto> questionDtoList=new ArrayList<>();

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<QuestionDto> getQuestionDtoList() {
        return questionDtoList;
    }

    public void setQuestionDtoList(List<QuestionDto> questionDtoList) {
        this.questionDtoList = questionDtoList;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public void setPages(Integer page, Integer total, Integer size) {
        this.page = page;
        this.total = total;
        showPrevious = page == 1 ? false : true;
        showNext = page == total ? false : true;
        pageList.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page + i <= total) {
                pageList.add(page + i);
            }
            if (page - i > 0) {
                pageList.add(0, page - i);
            }
        }
        showFirst = pageList.contains(1) ? false : true;
        showLast = pageList.contains(total) ? false : true;
        if(total==0){
            showPrevious=false;
            showLast=false;
            showNext=false;
            showFirst=false;

        }
    }
}
