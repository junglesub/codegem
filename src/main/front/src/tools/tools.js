// Function to detect URLs and convert them to hyperlinks
export const convertTextToLinks = (text) => {
  // Regular expression to detect URLs
  const urlRegex = /(https?:\/\/[^\s]+)/g;

  // Split the text by URLs and map over the parts
  return text.split(urlRegex).map((part, index) => {
    // If the part matches the URL regex, return a hyperlink
    if (part.match(urlRegex)) {
      return (
        <a href={part} key={index} target="_blank" rel="noopener noreferrer">
          {part}
        </a>
      );
    }
    // Otherwise, return the text as it is
    return <span key={index}>{part}</span>;
  });
};

export const removeDuplicates = (arr, key) => {
  const seen = new Set();
  return arr.filter((item) => {
    const val = item[key]; // Get the property value to check
    if (seen.has(val)) {
      return false; // Skip if it's already in the set
    }
    seen.add(val); // Otherwise, add to set and keep the item
    return true;
  });
};

export const getExtensionFromUrl = (url) => {
  const cleanUrl = url.split("?")[0]; // Remove anything after '?'
  return cleanUrl.split(".").pop().toLowerCase(); // Get the file extension
};

// Function to check if the URL is an image
export const isImage = (url) => {
  const imageExtensions = ["jpg", "jpeg", "png", "gif", "bmp", "svg"];
  const extension = getExtensionFromUrl(url); // Get cleaned extension
  return imageExtensions.includes(extension);
};

// Function to check if the URL is a video
export const isVideo = (url) => {
  const videoExtensions = ["mp4", "webm", "ogg", "mov", "avi"];
  const extension = getExtensionFromUrl(url); // Get cleaned extension
  return videoExtensions.includes(extension);
};
