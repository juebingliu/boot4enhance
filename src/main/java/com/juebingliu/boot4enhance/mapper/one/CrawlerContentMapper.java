package com.juebingliu.boot4enhance.mapper.one;

import com.juebingliu.boot4enhance.domain.one.CrawlerContent;
import com.juebingliu.boot4enhance.domain.one.CrawlerContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrawlerContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    long countByExample(CrawlerContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int deleteByExample(CrawlerContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int insert(CrawlerContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int insertSelective(CrawlerContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    List<CrawlerContent> selectByExample(CrawlerContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    CrawlerContent selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int updateByExampleSelective(@Param("record") CrawlerContent record, @Param("example") CrawlerContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int updateByExample(@Param("record") CrawlerContent record, @Param("example") CrawlerContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int updateByPrimaryKeySelective(CrawlerContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crawler_content
     *
     * @mbg.generated Wed Jun 05 16:22:41 CST 2019
     */
    int updateByPrimaryKey(CrawlerContent record);

    //批量插入
    int insertBatch(List<CrawlerContent> list);
}