package com.kataer.filter;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
public interface FilterChain {
  void doFilter(Request req, Response resp);
}
