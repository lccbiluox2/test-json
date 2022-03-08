//安恒情报映射
keyInside(.data)|
{
    "mclass":   "/"+.threat_type,
    "sclass":   "/"+.threat_type+"/"+ if .threat_subtype==null then "other" else .threat_subtype end,
    "confidence":   if .confidence_level<=10 and .confidence_level>=7 then "High"
                    elif .confidence_level<=6 and .confidence_level>=4 then "Medium"
                    else "Low" end,
    "IoCLevel": if .risk_level=5 then "Critical"
                elif .risk_level=4  then "High"
                elif .risk_level=3  then "Medium"
                elif .risk_level=2  then "Low"
                else "info" end,
    "IoCThreatName":    .warn_name,
    "tags": .tags,
    "createTime":   .create_time,
    "updateTime":   .update_time,
    "isApt":    .is_APT,
    "IoCIcon":  if .malicious then "恶意"
                else "正常" end,
    "TiName":   "AnHengTipApiIntelligenceSource",
    "ip":   .key
}