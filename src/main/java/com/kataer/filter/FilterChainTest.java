package com.kataer.filter;

/**
 * @author kataer
 * @description: this class for
 * @date 2022/2/17
 */
public class FilterChainTest {
  public static void main(String[] args) {
    FilterChainImpl filterChain = new FilterChainImpl();
    filterChain.doFilter(new Request(), new Response());
  }
}
