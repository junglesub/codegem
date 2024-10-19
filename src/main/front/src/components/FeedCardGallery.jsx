import "./FeedCardGallery.css";
import { getExtensionFromUrl, isImage, isVideo } from "../tools/tools";

const FeedCardGallery = ({ images = [] }) => {
  const filteredImages = images.filter((url) => isImage(url) || isVideo(url));
  if (filteredImages.length === 0) return <></>;
  return (
    <div className="FeedCardGallery">
      <div className={`post-images images-${filteredImages.length}`}>
        {filteredImages.slice(0, 3).map((url, index) =>
          isImage(url) ? (
            <img key={index} src={url} alt={`${index + 1}`} loading="lazy" />
          ) : isVideo(url) ? (
            <video key={index} controls>
              <source src={url} type={`video/${getExtensionFromUrl(url)}`} />
              Your browser does not support the video tag.
            </video>
          ) : (
            <>Unknown Data</>
          )
        )}
        {filteredImages.length >= 4 && (
          <div className="more-images-overlay">
            {filteredImages.length > 5 && <span>+{images.length - 4}</span>}
            <img key={1} src={images[3]} alt={`4`} loading="lazy" />
          </div>
        )}
      </div>
    </div>
  );
};

export default FeedCardGallery;
