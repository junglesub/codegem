import React, { useState, useEffect } from "react";
import { Modal, Box, Typography, Button } from "@mui/material";
import useDownloadPWA from "../../hooks/useDownloadPWA";
import { getDateString } from "../../tools/tools";

const style = {
  position: "fixed",
  bottom: "16px",
  left: "16px",
  width: 300,
  bgcolor: "background.paper",
  borderRadius: "8px",
  boxShadow: 24,
  p: 2,
};

const PWAInstallModal = () => {
  const [open, setOpen] = useState(false);
  const [deferredPrompt, handleDownload] = useDownloadPWA({
    onAccept: () => setOpen(false),
  });

  const isBrowserSupported = "BeforeInstallPromptEvent" in window;

  const lastModalDate = localStorage.getItem("lastPWASeen");

  const isPWAInstalled = () => {
    return (
      window.matchMedia("(display-mode: standalone)").matches ||
      window.navigator.standalone === true
    );
  };

  useEffect(() => {
    // Check if the app is running as a PWA
    if (!isPWAInstalled()) {
      setOpen(true); // Show modal if PWA is not installed
    }
  }, []);

  const handleInstallClick = () => {
    // Trigger the installation prompt if applicable
    handleDownload();
  };

  const handleClose = () => {
    localStorage.setItem("lastPWASeen", getDateString());
    setOpen(false); // Close the modal
  };

  if (lastModalDate == getDateString()) {
    return <></>;
  }

  return (
    <Modal open={open} onClose={handleClose}>
      <Box sx={style}>
        <Typography variant="h6">홈 화면에 추가해보세요!</Typography>
        <Typography variant="body2" sx={{ mb: 2, wordBreak: "keep-all" }}>
          한동피드를 홈 화면에 추가하여 빠르게 새로운 소식을 확인하세요! <br />
          <Typography variant="body2" sx={{ mt: 1 }}>
            설치가 안된다면{" "}
            <a
              href="https://jryoo.notion.site/11ceac6bb2ce8057844fd80918270385"
              target="_blank"
              rel="noopener noreferrer"
            >
              가이드
            </a>
            를 따라해보세요
          </Typography>
        </Typography>
        {isBrowserSupported && deferredPrompt ? (
          <Button
            variant="contained"
            color="primary"
            onClick={handleInstallClick}
          >
            설치
          </Button>
        ) : (
          <Button
            variant="contained"
            color="primary"
            href="https://jryoo.notion.site/11ceac6bb2ce8057844fd80918270385"
            target="_blank"
            rel="noopener noreferrer"
          >
            가이드보기
          </Button>
        )}
        <Button
          variant="text"
          color="secondary"
          onClick={handleClose}
          sx={{ ml: 1 }}
        >
          오늘 보지 않기
        </Button>
      </Box>
    </Modal>
  );
};

export default PWAInstallModal;
