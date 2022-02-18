package com.kataer.filter;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
public interface Filter {
  void doFilter(Request req, Response resp, FilterChain chain);
}
