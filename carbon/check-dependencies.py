#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Spring Boot 升级依赖检查脚本
检查项目中的依赖是否与 Spring Boot 2.7.18 兼容
"""

import os
import re
import xml.etree.ElementTree as ET
from pathlib import Path

def check_spring_boot_version(pom_file):
    """检查Spring Boot版本"""
    try:
        tree = ET.parse(pom_file)
        root = tree.getroot()
        
        # 查找parent中的spring-boot-starter-parent
        for parent in root.findall('.//parent'):
            for artifact_id in parent.findall('artifactId'):
                if artifact_id.text == 'spring-boot-starter-parent':
                    version_elem = parent.find('version')
                    if version_elem is not None:
                        return version_elem.text
        return None
    except Exception as e:
        print(f"解析 {pom_file} 时出错: {e}")
        return None

def check_properties(pom_file):
    """检查properties中的版本配置"""
    try:
        tree = ET.parse(pom_file)
        root = tree.getroot()
        
        properties = {}
        for prop in root.findall('.//properties/*'):
            properties[prop.tag] = prop.text
            
        return properties
    except Exception as e:
        print(f"解析 {pom_file} 的properties时出错: {e}")
        return {}

def check_dependencies(pom_file):
    """检查依赖列表"""
    try:
        tree = ET.parse(pom_file)
        root = tree.getroot()
        
        dependencies = []
        for dep in root.findall('.//dependencies/dependency'):
            group_id = dep.find('groupId')
            artifact_id = dep.find('artifactId')
            version = dep.find('version')
            
            if group_id is not None and artifact_id is not None:
                dependencies.append({
                    'groupId': group_id.text,
                    'artifactId': artifact_id.text,
                    'version': version.text if version is not None else 'managed'
                })
                
        return dependencies
    except Exception as e:
        print(f"解析 {pom_file} 的dependencies时出错: {e}")
        return []

def main():
    print("=" * 60)
    print("Spring Boot 升级依赖检查")
    print("=" * 60)
    
    # 查找所有pom.xml文件
    pom_files = list(Path('.').rglob('pom.xml'))
    
    if not pom_files:
        print("未找到pom.xml文件")
        return
    
    print(f"找到 {len(pom_files)} 个pom.xml文件")
    print()
    
    # 检查每个pom.xml文件
    for pom_file in pom_files:
        print(f"检查文件: {pom_file}")
        print("-" * 40)
        
        # 检查Spring Boot版本
        spring_boot_version = check_spring_boot_version(pom_file)
        if spring_boot_version:
            print(f"Spring Boot 版本: {spring_boot_version}")
            if spring_boot_version != '2.7.18':
                print("  ⚠️  需要升级到 2.7.18")
        else:
            print("Spring Boot 版本: 未找到")
        
        # 检查properties
        properties = check_properties(pom_file)
        if properties:
            print("Properties:")
            for key, value in properties.items():
                if 'spring' in key.lower() or 'cloud' in key.lower():
                    print(f"  {key}: {value}")
        
        # 检查依赖
        dependencies = check_dependencies(pom_file)
        if dependencies:
            print("主要依赖:")
            for dep in dependencies[:10]:  # 只显示前10个
                if any(keyword in dep['groupId'].lower() for keyword in ['spring', 'mysql', 'redis', 'rocketmq']):
                    print(f"  {dep['groupId']}:{dep['artifactId']}:{dep['version']}")
        
        print()
    
    print("=" * 60)
    print("检查完成！")
    print("=" * 60)
    
    # 提供升级建议
    print("\n升级建议:")
    print("1. 将 Spring Boot 版本升级到 2.7.18")
    print("2. 将 Spring Cloud 版本升级到 2021.0.8")
    print("3. 将 Spring Cloud Alibaba 版本升级到 2021.0.5.0")
    print("4. 检查并更新其他依赖的兼容性")
    print("5. 运行 'mvn dependency:tree' 检查依赖冲突")

if __name__ == "__main__":
    main()
