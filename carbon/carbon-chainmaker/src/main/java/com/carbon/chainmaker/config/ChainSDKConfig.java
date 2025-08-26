package com.carbon.chainmaker.config;

import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.chainmaker.sdk.*;
import org.chainmaker.sdk.config.NodeConfig;
import org.chainmaker.sdk.config.SdkConfig;
import org.chainmaker.sdk.utils.UtilsException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ChainSDKConfig {

    private static final String OPENSSL_PROVIDER = "openSSL";
    private static final String TLS_NEGOTIATION = "TLS";

    static final String ADMIN1_KEY_PATH = "crypto-config/wx-org1.chainmaker.org/user/admin1/admin1.sign.key";
    static final String ADMIN1_CERT_PATH = "crypto-config/wx-org1.chainmaker.org/user/admin1/admin1.sign.crt";
    static final String ADMIN2_KEY_PATH = "crypto-config/wx-org2.chainmaker.org/user/admin1/admin1.sign.key";
    static final String ADMIN2_CERT_PATH = "crypto-config/wx-org2.chainmaker.org/user/admin1/admin1.sign.crt";
    static final String ADMIN3_KEY_PATH = "crypto-config/wx-org3.chainmaker.org/user/admin1/admin1.sign.key";
    static final String ADMIN3_CERT_PATH = "crypto-config/wx-org3.chainmaker.org/user/admin1/admin1.sign.crt";

    static final String ORG_ID1 = "wx-org1.chainmaker.org";
    static final String ORG_ID2 = "wx-org2.chainmaker.org";
    static final String ORG_ID3 = "wx-org3.chainmaker.org";

    @Bean
    public ChainClient chainClient() throws IOException, SdkException {

        SdkConfig sdkConfig = getSdkConfig();

        List<Node> nodeList = new ArrayList<>();
        for (NodeConfig nodeConfig : sdkConfig.getChainClient().getNodes()) {
            List<byte[]> tlsCaCertList = new ArrayList<>();
            for (String rootPath : nodeConfig.getTrustRootPaths()){
                tlsCaCertList.add(this.getResourceFileBytes(rootPath));
            }

            byte[][] tlsCaCerts = new byte[tlsCaCertList.size()][];
            tlsCaCertList.toArray(tlsCaCerts);

            String url = "";

            if (nodeConfig.isEnableTls()){
                url = "grpcs://" + nodeConfig.getNodeAddr();
            }else {
                url = "grpc://" + nodeConfig.getNodeAddr();
            }
            Node node = new Node();
            node.setClientKeyBytes(this.getResourceFileBytes(sdkConfig.getChainClient().getUserKeyFilePath()));
            node.setClientCertBytes(this.getResourceFileBytes(sdkConfig.getChainClient().getUserCrtFilePath()));
            node.setTlsCertBytes(tlsCaCerts);
            node.setHostname(nodeConfig.getTlsHostName());
            node.setGrpcUrl(url);
            node.setSslProvider(OPENSSL_PROVIDER);
            node.setNegotiationType(TLS_NEGOTIATION);
            node.setConnectCount(nodeConfig.getConnCnt());
            node.setMessageSize(sdkConfig.getChainClient().getRpcClient().getMaxReceiveMessageSize());
            nodeList.add(node);
        }

        Node[] nodes = new Node[nodeList.size()];
        nodeList.toArray(nodes);

        ChainManager chainManager = ChainManager.getInstance();
        ChainClient chainClient = chainManager.getChainClient(sdkConfig.getChainClient().getChain_id());
        User clientUser = new User(sdkConfig.getChainClient().getOrgId(), this.getResourceFileBytes(sdkConfig.getChainClient().getUserKeyFilePath()),
                this.getResourceFileBytes(sdkConfig.getChainClient().getUserCrtFilePath()));
        if (chainClient == null) {
            chainClient = chainManager.createChainClient(sdkConfig.getChainClient().getChain_id(), clientUser, nodes);
        }
        return chainClient;
    }

    @Bean("adminUser1")
    public User getUser1() throws IOException, SdkException {
        return new User(ORG_ID1, this.getResourceFileBytes(ADMIN1_KEY_PATH),
                this.getResourceFileBytes(ADMIN1_CERT_PATH));
    }

    @Bean("adminUser2")
    public User getUser2() throws IOException, SdkException {
        return new User(ORG_ID2, this.getResourceFileBytes(ADMIN2_KEY_PATH),
                this.getResourceFileBytes(ADMIN2_CERT_PATH));
    }

    @Bean("adminUser3")
    public User getUser3() throws IOException, SdkException {
        return new User(ORG_ID3, this.getResourceFileBytes(ADMIN3_KEY_PATH),
                this.getResourceFileBytes(ADMIN3_CERT_PATH));
    }

    private SdkConfig getSdkConfig() throws IOException{
        Yaml yaml = new Yaml();
        InputStream in =  this.getClass().getClassLoader().getResourceAsStream("sdk_config.yml");
        SdkConfig sdkConfig;
        try{
            sdkConfig = yaml.loadAs(in, SdkConfig.class);
        } finally {
            in.close();
        }
        return sdkConfig;
    }


    public byte[] getResourceFileBytes(String resourcePath) throws UtilsException {
        try {
            return IOUtils.toByteArray((InputStream) Resources.getResource(resourcePath).getContent());
        } catch (IOException var2) {
            throw new UtilsException("get file by path err : " + var2.getMessage());
        }
    }

}
