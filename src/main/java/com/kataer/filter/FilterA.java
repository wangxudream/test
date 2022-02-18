package com.kataer.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
@Slf4j
public class FilterA implements Filter {
  private static final String name = "FilterA";

  @Override
  public void doFilter(Request req, Response resp, FilterChain chain) {
    log.info("enter :{}", name);
    chain.doFilter(req, resp);
    log.info("after :{}", name);
  }
}
