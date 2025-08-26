npm install
npm run build
version=v2.0.0
docker build -t registry.cn-shenzhen.aliyuncs.com/carbon88/carbon-saas-front-basic:$version .  # 镜像tag
sudo docker push registry.cn-shenzhen.aliyuncs.com/carbon88/carbon-saas-front-basic:$version # 上传到镜像库
