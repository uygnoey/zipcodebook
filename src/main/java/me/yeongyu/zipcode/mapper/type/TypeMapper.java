package me.yeongyu.zipcode.mapper.type;

import me.yeongyu.zipcode.model.type.LangType;
import me.yeongyu.zipcode.model.type.SearchLangType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeMapper {

    String BASIC_QUERY =
            "SELECT @index := @index + 1 as idx, t.* "+
            "FROM type t "+
            "JOIN (SELECT @index := 0) idx " +
            "<trim prefix='WHERE' prefixOverrides='AND |OR'> "+
            "   <if test='id != null'> "+
            "       id = #{id} "+
            "   </if> "+
            "   <if test='type != null'> "+
            "       AND type = #{type} "+
            "   </if> "+
            "   <choose> "+
            "       <when test='startDate != null and endDate != null'> "+
            "           AND update_date BETWEEN #{startDate} AND #{endDate} "+
            "       </when> "+
            "       <when test='startDate != null'> "+
            "           AND update_date <![CDATA[>=]]> #{startDate} "+
            "       </when> "+
            "       <when test='endDate != null'> "+
            "           AND update_date <![CDATA[<=]]> #{endDate} "+
            "       </when> "+
            "   </choose> "+
            "</trim> "+
            "ORDER BY t.id, t.type";

    @Select({
            "<script> ",
            "   SELECT * ",
            "   FROM ( ",
                    BASIC_QUERY,
            "   ) result ",
            "   <if test='limit != null'> ",
            "       LIMIT ${limit} ",
            "   </if> ",
            "   <if test='offset != null'> ",
            "       OFFSET ${offset} ",
            "   </if> ",
            "</script>"
    })
    List<LangType> getList(SearchLangType langType);

    @Select({
            "<script> ",
            "   SELECT count(*) ",
            "   FROM ( ",
                    BASIC_QUERY,
            "   ) result ",
            "</script>"
    })
    Integer getCount(SearchLangType langType);

    @Select({
            "<script> ",
            "   SELECT * ",
            "   FROM type t ",
            "   <trim prefix='WHERE' prefixOverrides='AND |OR'> ",
            "      <if test='id != null'> ",
            "          id = #{id} ",
            "      </if> ",
            "      <if test='type != null'> ",
            "          AND type = #{type} ",
            "      </if> ",
            "   </trim> ",
            "</script>"
    })
    LangType getOne(LangType langType);

    @Insert({
            "INSERT type (type) values (#{type})"
    })
    int insert(String type);

    @Update({
            "<script> ",
            "    UPDATE type ",
            "       <trim prefix='SET' suffixOverrides=','> ",
            "           <if test='type != null'>type = #{type},</if> ",
            "           update_date = CURRENT_TIMESTAMP ",
            "       </trim> ",
            "    WHERE id = #{id} ",
            "</script>"
    })
    int update(LangType langType);

    @Delete({
            "DELETE FROM type WHERE id = #{id}"
    })
    int delete(Integer id);
}
