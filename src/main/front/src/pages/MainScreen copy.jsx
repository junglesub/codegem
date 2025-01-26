import { useRecoilValue, useResetRecoilState } from "recoil";
import { authJwtAtom } from "../recoil/authAtom";


import React, { useEffect, useState } from 'react';
import MainDisplay from "../components/MainDisplay";
import { Card, CardContent, Typography, TextField, Button, Box } from "@mui/material";
import { fetchBe } from "../tools/api";
import FeedCard from "../components/FeedCard";

const MainScreen = ({ noRedirect = false }) => {
  const authData = useRecoilValue(authJwtAtom);
  const logout = useResetRecoilState(authJwtAtom);
  // if (authData) return <>Welcome</>;

  const [allAlgData, setAllAlgData] = useState([]);
  const refreshData = () => fetchBe(null, "/algsolve/get").then(data => setAllAlgData(data.sort((a, b) => new Date(b.createdDate) - new Date(a.createdDate))))
  useEffect(() => {
    refreshData();
  }, []);

  const [formData, setFormData] = useState({
    githubRepoId: '',
    githubFilePath: '',
    githubIssueId: '',
    userId: 'testUserId',
    message: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = () => {
    // Submit formData to API or handle form submission logic
    console.log(formData);
    fetchBe(null, "/algsolve/create", "POST", formData).then(() => {
      setFormData({
        githubRepoId: '',
        githubFilePath: '',
        githubIssueId: '',
        userId: 'testUserId',
        message: ''
      });
      refreshData();
    }).catch(data => alert(data))
  };
  return (
    <>
      <MainDisplay>
        <Card sx={{ my: 2 }}>
          <CardContent>
            <Typography variant="h6" component="div">
              코드리뷰 등록하기
            </Typography>
            <Box component="form" sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
              <TextField
                label="GitHub 리포아이디"
                variant="outlined"
                name="githubRepoId"
                value={formData.githubRepoId}
                onChange={handleChange}
                required
              />
              <TextField
                label="GitHub 경로"
                variant="outlined"
                name="githubFilePath"
                value={formData.githubFilePath}
                onChange={handleChange}
                required
              />
              <TextField
                label="GitHub 이슈아이디"
                variant="outlined"
                name="githubIssueId"
                value={formData.githubIssueId}
                onChange={handleChange}
                required
              />
              <TextField
                label="유저아이디"
                variant="outlined"
                name="userId"
                value={formData.userId}
                // onChange={handleChange}
                required
              />
              <TextField
                label="코멘트"
                variant="outlined"
                name="message"
                value={formData.message}
                onChange={handleChange}
                required
                multiline
                rows={4}
              />
              <Button variant="contained" onClick={handleSubmit}>
                등록
              </Button>
            </Box>
          </CardContent>
        </Card>
        {allAlgData.map((item) => (
          // <FeedItemNew
          //   key={item.id}
          //   item={item}
          //   setAllSeenFeedId={setAllSeenFeedId}
          // />
          <FeedCard key={item.id} item={item} />
        ))}
      </MainDisplay>
    </>
  );
};

export default MainScreen;
