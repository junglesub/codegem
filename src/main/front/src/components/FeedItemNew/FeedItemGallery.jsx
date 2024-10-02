import React from "react";

import "./FeedItemGallery.css";
import { getExtensionFromUrl, isImage, isVideo } from "../../tools/tools";

const FeedItemGallery = ({ images = [] }) => {
  if (images.length === 0) return <></>;
  return (
    <div className={`post-images images-${images.length}`}>
      {images.slice(0, 3).map((url, index) =>
        isImage(url) ? (
          <img key={index} src={url} alt={`${index + 1}`} />
        ) : isVideo(url) ? (
          <video key={index} controls>
            <source src={url} type={`video/${getExtensionFromUrl(url)}`} />
            Your browser does not support the video tag.
          </video>
        ) : (
          <>Unknown Data</>
        )
      )}
      {images.length >= 4 && (
        <div className="more-images-overlay">
          {images.length > 5 && <span>+{images.length - 4}</span>}
          <img key={1} src={images[3]} alt={`4`} />
        </div>
      )}
    </div>
  );
};

export default FeedItemGallery;
