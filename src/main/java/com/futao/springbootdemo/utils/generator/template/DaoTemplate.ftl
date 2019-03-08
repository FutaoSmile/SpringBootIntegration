package ${daoPackagePath};

import ${entityPackagePath}.${className};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* ${classDesc}Dao层
*
* @author futao
* Created on ${createDate}.
*/
@Mapper
public interface ${className}Dao {

/**
* 新增${classDesc}
*
* @return ${classDesc}
*/
${className} add(${className} ${className?lower_case});

/**
* 删除${classDesc}
*
* @param id 要删除的${classDesc}主键
* @return ${classDesc}
*/
void delete(String id);


/**
* 修改${classDesc}
*
* @param ${className?lower_case}
* @param ${className?lower_case} 要修改的${classDesc}
* @return ${classDesc}
*/
void update(${className} ${className?lower_case});

/**
* 查询${classDesc}列表
*
* @return ${classDesc}列表
*/
List<${className}> list();

/**
* 获取${classDesc}详情
*
* @param id 要查询的${classDesc}主键
* @return ${classDesc}
*/
${className} byId(String id);
}
