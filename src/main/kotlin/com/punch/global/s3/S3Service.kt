package com.punch.global.s3

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Service
class S3Service(
    private val amazonS3Client: AmazonS3Client,
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun uploadFile(file: MultipartFile) : String {
        val fileName = "${UUID.randomUUID()}-${file.name}"
        S3Upload(file, fileName)

        return getResourceUrl(fileName)
    }

    private fun S3Upload(file: MultipartFile, fileName: String) {
        val inputStream = file.inputStream
        val objectMetadata = ObjectMetadata().apply {
            this.contentLength = file.size
            this.contentType = file.contentType
        }
        amazonS3Client.putObject(
            PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )
    }

    fun getResourceUrl(fileName: String) : String {
        return amazonS3Client.getResourceUrl(bucket, fileName)
    }

    fun uploadUrl(fileName: String) : String {

        val generatePresignedUrlRequest =
            GeneratePresignedUrlRequest(bucket, fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(Timestamp.valueOf(LocalDateTime.now().plusHours(4)))


        return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest).toString()

    }
}