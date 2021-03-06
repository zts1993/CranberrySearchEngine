/*
 * Copyright (c) 2015 By Timothy Zhang
 */

package com.zts1993.gse.bean;

/**
 * Created by TianShuo on 2015/4/4.
 */
public class Pagination {

    private int curPage = 1; // 当前页
    private int pageSize = 10; // 每页多少行
    private int totalRow; // 共多少行
    private int start;// 当前页起始行
    private int end;// 结束行
    private int totalPage; // 共多少页

    public Pagination(int pageSize, int totalRow) {
        this.pageSize = pageSize;
        this.totalRow = totalRow;
        if (totalRow % pageSize == 0) {
            this.totalPage = totalRow / pageSize;
        } else {
            this.totalPage = totalRow / pageSize + 1;
        }

    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        if (curPage < 1) {
            curPage = 1;
        } else {
            start = pageSize * (curPage - 1);
        }
        end = start + pageSize > totalRow ? totalRow : start + pageSize;
        this.curPage = curPage;
    }

    public int getStart() {
        // start=curPage*pageSize;
        return start;
    }

    public int getEnd() {
        return end;
    }


    public int getTotalRow() {
        return totalRow;
    }

//    public void setTotalRow(int totalRow) {
//        totalPage = (totalRow + pageSize - 1) / pageSize;
//        this.totalRow = totalRow;
//        if (totalPage < curPage) {
//            curPage = totalPage;
//            start = pageSize * (curPage - 1);
//            end = totalRow;
//        }
//        end = start + pageSize > totalRow ? totalRow : start + pageSize;
//    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }


    @Override
    public String toString() {
        return "Pager{" +
                "curPage=" + curPage +
                ", pageSize=" + pageSize +
                ", totalRow=" + totalRow +
                ", start=" + start +
                ", end=" + end +
                ", totalPage=" + totalPage +
                '}';
    }
}