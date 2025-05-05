package com.project.ResumeBuilder.dtos;

import java.util.List;

public class PaginatedResponse<T> {

    private List<T> data;
    private Pagination pagination;

    public PaginatedResponse(List<T> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<T> getData() {
        return data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public static class Pagination {
        private long total;
        private int per_page;
        private int current_page;
        private int total_pages;

        public Pagination(long total, int per_page, int current_page, int total_pages) {
            this.total = total;
            this.per_page = per_page;
            this.current_page = current_page;
            this.total_pages = total_pages;
        }

        public long getTotal() {
            return total;
        }

        public int getPer_page() {
            return per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public int getTotal_pages() {
            return total_pages;
        }
    }
}
