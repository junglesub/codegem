import React, { useEffect, useState } from "react";
import MainDisplay from "../MainDisplay";
import { Box, Button, Card, CardContent, Typography } from "@mui/material";

import { ActionList } from "@primer/react";
import { FileDirectoryIcon } from "@primer/octicons-react";
import { useFetchBe, useFetchGh } from "../../tools/api";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userDetailAtom } from "../../recoil/userAtom";

function FileList({ setSelectedFiles }) {
  const userDetail = useRecoilValue(userDetailAtom);
  const navigate = useNavigate();
  const fetchBe = useFetchBe();
  const fetchGh = useFetchGh();
  const [allFiles, setAllFiles] = useState([]);
  const [solvedFiles, setSolvedFiles] = useState([]);

  const [selectedIndices, setSelectedIndices] = React.useState([]);

  const handleSelect = (index) => {
    if (!index) return;
    if (selectedIndices.includes(index)) {
      setSelectedIndices(selectedIndices.filter((i) => i !== index));
    } else {
      setSelectedIndices([...selectedIndices, index]);
    }
  };

  useEffect(() => {
    {
      fetchGh(
        `https://api.github.com/repos/${userDetail.db.githubRepo}/git/trees/HEAD?recursive=true`
      ).then((files) => setAllFiles(files.tree));

      fetchBe("/solution/solved").then((doc) => setSolvedFiles(doc.paths));
    }
  }, [userDetail]);
  console.log(solvedFiles);
  return (
    <MainDisplay>
      <Card sx={{ my: 2 }}>
        <CardContent>
          <Box
            display="flex"
            sx={{ my: 2 }}
            gap={2}
            justifyContent="space-between"
          >
            <Button component={Link} variant="contained" to="/">
              홈화면
            </Button>
            <Typography
              flexGrow={1}
              variant="h5"
              component="div"
              align="center"
            >
              파일 선택
            </Typography>
          </Box>

          <ActionList
            role="menu"
            selectionVariant="multiple"
            aria-label="Project"
          >
            {/* <ActionList.Heading as="h2" size="small">
              1단계. Repo 선택
            </ActionList.Heading> */}
            {allFiles
              .filter(
                (file) =>
                  file.type === "blob" && solvedFiles.includes(file.path)
              )
              .map((repo, index) => (
                <ActionList.Item
                  key={repo.path}
                  role="menuitemcheckbox"
                  selected={selectedIndices.includes(repo.path)}
                  aria-checked={selectedIndices.includes(repo.path)}
                  onSelect={() => handleSelect(repo.path)}
                >
                  <ActionList.LeadingVisual>
                    <FileDirectoryIcon />
                  </ActionList.LeadingVisual>
                  {repo.path}
                </ActionList.Item>
              ))}
          </ActionList>
          <Button
            onClick={() => {
              setSelectedFiles([...selectedIndices]);
            }}
          >
            다음
          </Button>
        </CardContent>
      </Card>
    </MainDisplay>
  );
}

export default FileList;
