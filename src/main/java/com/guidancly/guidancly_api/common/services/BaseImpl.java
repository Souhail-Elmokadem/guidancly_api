package com.guidancly.guidancly_api.common.services;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.user.services.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
@Host
public class BaseImpl {

    private JwtEncoder jwtEncoder;
    private UserManager userManager;

    private JwtDecoder jwtDecoder;
    public UserDto getUserByToken(String token){
        String jwtToken = token.replace("Bearer ", "");
        Jwt decodedToken = jwtDecoder.decode(jwtToken);
        Map<String, Object> claims = decodedToken.getClaims();
        UserDto userDto = userManager.getUserByEmail(claims.get("sub").toString());
        return userDto;
    }
    public String uploadfile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("Uploaded file is empty or null");
        }

        String directoryPath = "path/to/your/directory"; // Update this to your actual directory path
        if (!Files.exists(Paths.get(directoryPath))) {
            Files.createDirectories(Paths.get(directoryPath));
        }

        String originalFilename = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        String suffix = "/" + UUID.randomUUID().toString();
        String filePATH = directoryPath + originalFilename + suffix;
        String fileUrl = Host.HOSTNAME_FILE + originalFilename + suffix;

        try {
            Files.write(Paths.get(filePATH), file.getBytes());
        } catch (IOException e) {
            throw new IOException("Failed to write file to disk", e);
        }

        return fileUrl;
    }

    public String uploadBase64Image(String base64Image) throws IOException {
        if (base64Image == null || base64Image.isEmpty()) {
            throw new IOException("Base64 image string is empty or null");
        }

        // Decode Base64 to bytes
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

        // Construct the file name and path
        String directoryPath = Host.LOCAL; // Update this to your actual directory path
        if (!Files.exists(Paths.get(directoryPath))) {
            Files.createDirectories(Paths.get(directoryPath));
        }

        String fileName = UUID.randomUUID().toString() + ".jpg"; // Adjust the extension based on the image type
        String filePath = directoryPath + "/" + fileName;
        String fileUrl = Host.HOSTNAME_FILE  + fileName;

        // Save the file
        try {
            Files.write(Paths.get(filePath), decodedBytes);
        } catch (IOException e) {
            throw new IOException("Failed to write file to disk", e);
        }

        return fileUrl;
    }


}
