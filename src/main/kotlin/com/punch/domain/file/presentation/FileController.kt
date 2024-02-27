package com.punch.domain.file.presentation

import com.punch.global.s3.S3Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/file")
class FileController(
    private val s3Service: S3Service
) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("image") file: MultipartFile) : String {
        return s3Service.uploadFile(file)
    }

}