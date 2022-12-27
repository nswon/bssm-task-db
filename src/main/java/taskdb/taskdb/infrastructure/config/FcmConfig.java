package taskdb.taskdb.infrastructure.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.notification.CreateFirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class FcmConfig {
    private static final String FIREBASE_CONFIG_PATH = "taskdb-b4857-firebase-adminsdk-bbv2u-ff04edbe81.json";
    private static final String PROJECT_ID = "154549871574";

    @PostConstruct
    public void initialize() throws IOException {
        FirebaseOptions options = CreateFirebaseOptions.create(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream(), PROJECT_ID);
        FirebaseApp.initializeApp(options);
    }
}
