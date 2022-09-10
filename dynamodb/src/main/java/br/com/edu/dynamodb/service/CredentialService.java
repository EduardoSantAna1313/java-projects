package br.com.edu.dynamodb.service;

import java.util.Base64;

import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleWithSamlCredentialsProvider;
import software.amazon.awssdk.services.sts.model.AssumeRoleWithSamlResponse;

public class CredentialService {

    public AwsCredentials getAwsCredentials(String roleArn, String principalArn, String samlResponse, Region region) {
        StsAssumeRoleWithSamlCredentialsProvider.builder();
        StsClient stsClient = StsClient.builder().credentialsProvider(AnonymousCredentialsProvider.create())
                .region(region).build();
        AssumeRoleWithSamlResponse resp = stsClient.assumeRoleWithSAML(builder -> {
            String samlAssertionb64 = Base64.getEncoder().encodeToString(samlResponse.getBytes());
            builder.roleArn(roleArn).principalArn(principalArn).samlAssertion(samlAssertionb64);
        });

        String accessKey = resp.credentials().accessKeyId();
        String secretKey = resp.credentials().secretAccessKey();
        String sessionToken = resp.credentials().sessionToken();

        return AwsSessionCredentials.create(accessKey, secretKey, sessionToken);
    }

}
