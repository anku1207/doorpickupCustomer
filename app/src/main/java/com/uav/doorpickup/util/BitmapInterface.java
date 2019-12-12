package  com.uav.doorpickup.util;





import com.uav.doorpickup.vo.BitmapVO;

import java.util.List;

public interface BitmapInterface {

    void downloadComplete(List<BitmapVO> bitmapVOs);
    void error(String error);
}
