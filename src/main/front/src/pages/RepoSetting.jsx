import { useRecoilValue, useResetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";

import React, { useEffect, useState } from "react";
import MainDisplay from "../components/MainDisplay";
import {
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Box,
} from "@mui/material";
import { fetchBe } from "../tools/api";
import FeedCard from "../components/FeedCard";
import { ghOauthClientId } from "../constants";
import { userDetailAtom } from "../recoil/userAtom";
import SetRepo from "../components/repo/SetRepo";
import FileList from "../components/repo/FileList";
import FileLink from "../components/repo/FileLink";

const RepoSettingPage = ({ noRedirect = false }) => {
  const authData = useRecoilValue(authJwtAtom);
  const logout = useResetRecoilState(authJwtAtom);
  const userDetail = useRecoilValue(userDetailAtom);
  // if (authData) return <>Welcome</>;
  console.log(userDetail?.db);

  const [selectedFiles, setSelectedFiles] = useState([
    // "week1/A077_유정섭_20250109.py",
    // "week1/A081_유정섭_20250109.py",
    // "week1/A106_유정섭_20250109.py",
  ]);

  if (userDetail?.db?.githubRepo === null) {
    return <SetRepo />;
  }
  if (!selectedFiles || selectedFiles.length == 0) {
    return <FileList setSelectedFiles={setSelectedFiles} />;
  }
  return (
    <FileLink
      selectedFiles={selectedFiles}
      setSelectedFiles={setSelectedFiles}
      repo={userDetail.db.githubRepo}
    />
  );
};

export default RepoSettingPage;
