import React, { useEffect, useState } from "react";
import MainDisplay from "../MainDisplay";
import { Button, Card, CardContent, Typography } from "@mui/material";

import { ActionList } from "@primer/react";
import { FileDirectoryIcon } from "@primer/octicons-react";
import { useFetchBe, useFetchGh } from "../../tools/api";
import { useNavigate } from "react-router-dom";

function SetRepo() {
  const navigate = useNavigate();
  const fetchBe = useFetchBe();
  const fetchGh = useFetchGh();
  const [allRepos, setAllRepos] = useState([]);
  useEffect(() => {
    fetchGh("https://api.github.com/user/installations").then((data) =>
      Promise.all(
        data.installations.map(
          async (install) =>
            (
              await fetchGh(
                `https://api.github.com/user/installations/${install.id}/repositories`
              )
            ).repositories
        )
      ).then((result) => setAllRepos(...result))
    );
  }, []);
  console.log(allRepos);
  return (
    <MainDisplay>
      <Card sx={{ my: 2 }}>
        <CardContent>
          <Typography
            sx={{ mb: 2 }}
            variant="h5"
            component="div"
            align="center"
          >
            Repo 선택
          </Typography>
          <Button
            as="a"
            variant="contained"
            href="https://github.com/apps/codegem-app/installations/new"
            target="_blank"
            rel="noopener noreferrer"
          >
            CodeGem 앱을 리포에 추가하기
          </Button>
          <Typography sx={{ mt: 1 }} variant="body2" component="div">
            추가하고 새로고침 해주세요!
          </Typography>
          <ActionList>
            {/* <ActionList.Heading as="h2" size="small">
              1단계. Repo 선택
            </ActionList.Heading> */}
            <ActionList.Group>
              <ActionList.GroupHeading as="h3">
                Repositories
              </ActionList.GroupHeading>
              {allRepos?.map((repo) => (
                <ActionList.Item
                  key={repo.id}
                  onClick={() => {
                    fetchBe("/tbuser/ghRepo", "POST", {
                      repo: repo.full_name,
                    }).then(() => {
                      window.location.href = "/repo";
                    });
                  }}
                >
                  <ActionList.LeadingVisual>
                    <FileDirectoryIcon />
                  </ActionList.LeadingVisual>
                  {repo.full_name}
                </ActionList.Item>
              ))}
            </ActionList.Group>
          </ActionList>
        </CardContent>
      </Card>
    </MainDisplay>
  );
}

export default SetRepo;
