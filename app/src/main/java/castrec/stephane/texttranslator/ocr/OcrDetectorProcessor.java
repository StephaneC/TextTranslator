package castrec.stephane.texttranslator.ocr;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import android.util.Log;
import android.util.SparseArray;

import castrec.stephane.texttranslator.ui.camera.GraphicOverlay;


/**
 * Created by administrateur on 13/07/16.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {

  private GraphicOverlay<OcrGraphic> mGraphicOverlay;

  public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
    mGraphicOverlay = ocrGraphicOverlay;
  }

  @Override
  public void receiveDetections(Detector.Detections<TextBlock> detections) {
    mGraphicOverlay.clear();
    SparseArray<TextBlock> items = detections.getDetectedItems();
    for (int i = 0; i < items.size(); ++i) {
      TextBlock item = items.valueAt(i);
      if (item != null && item.getValue() != null) {
        Log.d("Processor", "Text detected! " + item.getValue());
      }
      OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
      mGraphicOverlay.add(graphic);
    }
  }

  @Override
  public void release() {
    mGraphicOverlay.clear();
  }


}
