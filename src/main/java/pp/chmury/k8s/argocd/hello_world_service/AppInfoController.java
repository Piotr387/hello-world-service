package pp.chmury.k8s.argocd.hello_world_service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppInfoController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public String getAppInfo() {

        // Get the number of available processors
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // Get the total amount of RAM in bytes
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        long totalMemory = ((com.sun.management.OperatingSystemMXBean) osBean).getTotalMemorySize();

        return String.format("""
                Production cluster update. <br>
                Application Name: %s, <br>
                Version: %s, <br>
                Number of CPUs: %d <br>
                Total RAM: %.2f GB <br>
                """, appName, appVersion, availableProcessors, totalMemory / (1024.0 * 1024 * 1024));
    }
}
