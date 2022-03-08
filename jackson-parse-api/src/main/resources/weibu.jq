//微步情报转换
.content[]|valueInside(.now;
{IoC:.data};
{IoCType:.data_type}
)|map(
  {
      IoC,
      mclass:
        if .type == "C2" then "/C2"
        elif .type =="zombie" then "/C2"
  		elif .type =="Sinkhole C2" then "/C2"
  		elif .type =="Botnet" then "/C2"
  		elif .type =="Malware" then "/Malware"
  		elif .type =="Phishing" then "/Phishing"
  		elif .type =="Scanner" then "/Scanner"
  		elif .type =="Spam" then "/Spam"
  		elif .type =="Suspicious" then "/Suspicious"
  		elif .type =="Compromised" then "/Compromised"
  		elif .type =="Whitelist" then "/WhiteList"
  		elif .type =="IDC" then "/Info"
  		elif .type =="Dynamic IP" then "/Info"
  		elif .type =="DDNS" then "/Info"
  		elif .type =="Proxy" then "/Info"
  		elif .type =="Bogon" then "/Info"
  		elif .type =="FullBogon" then "/Info"
  		elif .type =="HTTP Proxy In" then "/Info"
  		elif .type =="HTTP Proxy Out" then "/Info"
  		elif .type =="Socks Proxy In" then "/Info"
  		elif .type =="Socks Proxy Out" then "/Info"
  		elif .type =="VPN" then "/Info"
  		elif .type =="VPN In" then "/Info"
  		elif .type =="VPN Out" then "/Info"
        else null end,
  	sclass:
  		if .type == "C2" then "/C2/Others"
          elif .type =="zombie" then "/C2/Others"
  		elif .type =="Sinkhole C2" then "/C2/Sinkhole"
  		elif .type =="Botnet" then "/C2/Others"
  		elif .type =="Malware" then "/Malware/Others"
  		elif .type =="Phishing" then "/Phishing/Others"
  		elif .type =="Scanner" then "/Scanner/Others"
  		elif .type =="Spam" then "/Spam/Others"
  		elif .type =="Suspicious" then "/Suspicious/Others"
  		elif .type =="Compromised" then "/Compromised/Others"
  		elif .type =="Whitelist" then "/WhiteList/Others"
  		elif .type =="IDC" then "/Info/IDC"
  		elif .type =="Dynamic IP" then "/Info/DynamicIP"
  		elif .type =="DDNS" then "/Info/DNS"
  		elif .type =="Proxy" then "/Info/Proxy"
  		elif .type =="Bogon" then "/Info/Bogon"
  		elif .type =="FullBogon" then "/Info/Others"
  		elif .type =="HTTP Proxy In" then "/Info/Proxy"
  		elif .type =="HTTP Proxy Out" then "/Info/Proxy"
  		elif .type =="Socks Proxy In" then "/Info/Proxy"
  		elif .type =="Socks Proxy Out" then "/Info/Proxy"
  		elif .type =="VPN" then "/Info/VPN"
  		elif .type =="VPN In" then "/Info/VPN"
  		elif .type =="VPN Out" then "/Info/VPN"
          else null end,
  	tags:
  		if .type == "C2" then ["C&C"]
        elif .type =="zombie" then ["C&C","远程控制"]
  		elif .type =="Sinkhole C2" then ["C&C","Sinkhole"]
  		elif .type =="Botnet" then ["C&C","僵尸网络Botnet"]
  		elif .type =="Malware" then ["恶意软件"]
  		elif .type =="Phishing" then ["钓鱼地址"]
  		elif .type =="Scanner" then ["扫描器节点"]
  		elif .type =="Spam" then ["垃圾邮件"]
  		elif .type =="Compromised" then ["远程控制"]
  		elif .type =="Whitelist" then ["WhiteList"]
  		elif .type =="Proxy" then ["proxy代理"]
  		elif .type =="HTTP Proxy In" then ["proxy代理"]
  		elif .type =="HTTP Proxy Out" then ["proxy代理"]
  		elif .type =="Socks Proxy In" then ["proxy代理"]
  		elif .type =="Socks Proxy Out" then ["proxy代理"]
        else [] end,
  	confidence:
  		if .confidence>=80 and .confidence<=100 then "High"
  		elif .confidence>=60 and .confidence<80 then "Medium"
  		else "Low" end,
  	IoCLevel:
  		if .severity=="critical" then "Critical"
  		elif .severity=="high" then "High"
  		elif .severity=="medium" then "Medium"
  		elif .severity=="low" then "Low"
  		else "Info" end,
  	IoCType,
  	TiName:"WeiBuApiIntelligenceSource"
  })|selectN(.mclass!=null;1)
