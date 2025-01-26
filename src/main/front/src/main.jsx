import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { RecoilRoot } from "recoil";
import LoginProtected from "./components/LoginProtected";
import MainScreen from "./pages/MainScreen";

import { register as registerServiceWorker } from "./serviceWorkerRegistration";
import { createTheme, ThemeProvider } from "@mui/material";
import {
  ThemeProvider as PrimereThemeProvider,
  BaseStyles,
} from "@primer/react";

import MainFeed from "./pages/MainFeed";
import PWAInstallModal from "./components/modals/PWAInstallModal";
import GithubLoginCb from "./components/GithubLoginCb";
import RepoSettingPage from "./pages/RepoSetting";
import ReviewScreenPage from "./pages/ReviewScreen";

const router = createBrowserRouter([
  {
    path: "/land",
    element: <MainScreen />,
  },
  {
    path: "/",
    element: <LoginProtected comp={MainFeed} />,
  },
  {
    path: "/repo",
    element: <LoginProtected comp={RepoSettingPage} />,
  },
  {
    path: "/review/:uid",
    element: <LoginProtected comp={ReviewScreenPage} />,
  },
  {
    path: "/review/:uid/:issueId",
    element: <LoginProtected comp={ReviewScreenPage} />,
  },
  {
    path: "/gh",
    element: <GithubLoginCb />,
  },
]);

const theme = createTheme({
  palette: {
    mode: "dark",
  },
  typography: {
    fontFamily:
      '-apple-system, BlinkMacSystemFont, "Apple SD Gothic Neo", "Pretendard Variable", Pretendard, Roboto, "Noto Sans KR", "Segoe UI", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;',
  },
});

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <PrimereThemeProvider colorMode="dark">
        <BaseStyles>
          <RecoilRoot>
            <RouterProvider router={router} />
            <PWAInstallModal />
          </RecoilRoot>
        </BaseStyles>
      </PrimereThemeProvider>
    </ThemeProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

registerServiceWorker();
